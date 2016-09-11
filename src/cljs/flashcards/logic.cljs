(ns flashcards.logic
  "Game logic. Everything here should be pure functions"
  (:require
   [cljs.spec :as s]
   [clojure.set :as set]
   [flashcards.dicts.dicts :as dicts]
   [flashcards.turn :as turn]
   [flashcards.utils :as utils]
   ))

(s/check-asserts true)

;;; [TODO] Several of these functions still generate random state. All randomness should
;;; come in via the parameters, to be explicit and controllable by the testing harness.

(defn- init-game [db]
  (-> db
      (assoc-in [:dynamic :score] 0)
      (assoc-in [:dynamic :multiplier] 1)
      (assoc-in [:dynamic :bucketed-dictionary]
                (-> db (get-in [:options :dictionary]) dicts/get-dictionary dicts/init-dictionary))
      (assoc-in [:dynamic :active-buckets] (inc (rand-int (get-in db [:options :num-buckets]))))
      (assoc-in [:turn] (s/assert ::turn/turn nil))))

(defn- get-word [db]
  (let [word-items (get-in db [:dynamic :bucketed-dictionary :words])
        num-active-buckets (get-in db [:dynamic :active-buckets])
        available (filter #(< (::turn/bucket %) num-active-buckets) word-items)]
    (when-not (zero? (count available))
      (rand-nth available))))

(defn- get-other-words [db correct-word]
  (let [word-items (get-in db [:dynamic :bucketed-dictionary :words])
        num-needed (dec (get-in db [:options :num-choices]))]
    (->> word-items
         shuffle
         (remove #(= % correct-word))
         (take num-needed))))

;; [TODO] Refactor "choice" to "word" or "word-item" soon
(defn- turn-data [db correct-choice other-choices]
  (let [direction (get-in db [:options :direction])
        forward? (or (= direction :new-to-known)
                     (and (= direction :both) (zero? (rand-int 2))))
        word ((if forward? ::turn/word ::turn/translation) correct-choice)
        translation ((if forward? ::turn/translation ::turn/word) correct-choice)
        other-translations (map (if forward? ::turn/translation ::turn/word) other-choices)
        translation-choices (shuffle (conj other-translations translation))]
    {::turn/word word
     ::turn/correct-choice correct-choice
     ::turn/other-choices other-choices
     ::turn/translation translation
     ::turn/forward? forward?
     ::turn/translation-choices translation-choices}))

(defn- setup-turn [db]
  (let [correct-word (get-word db)
        other-words (get-other-words db correct-word)]
    (assoc db :turn
           (s/assert ::turn/turn
                      (merge (:turn db)
                             {::turn/text ""}
                             (turn-data db correct-word other-words))))))

(defn first-turn [db]
  (-> db init-game setup-turn))

(defn turn-points [& {:keys [::turn/players-answer ::turn/correct-answer options]}]
  (let [base-wrong -10
        base-right 10
        free-text? (= :free-text (:show-choices options))
        num-choices (:num-choices options)
        choices-multiplier (if (< num-choices 5) 1.0 (- num-choices 3.5))
        direction-multiplier (case (:direction options)
                               :new-to-known 1.0
                               :known-to-new 1.6
                               :both 2.0)]
    (if (= players-answer correct-answer)
      (if free-text? 100 (* base-right direction-multiplier choices-multiplier))
      (if free-text? -10 (/ base-wrong direction-multiplier)))))

(defn- prev-bucket [db bucket]
  (max (dec bucket) 0))

(defn- next-bucket [db bucket]
  (let [num-buckets (get-in db [:options :num-buckets])]
    (min (inc bucket) (dec num-buckets))))

(defn update-word-score [db word-item correct?]
  (let [dict-name (get-in db [:dynamic :bucketed-dictionary :name])
        word-pos (first (keep-indexed (fn [idx x] (when (= x word-item) idx))
                                      (get-in db [:dynamic :bucketed-dictionary :words])))
        bucket (partial (if correct? next-bucket prev-bucket) db)
        new-item (update word-item ::turn/bucket bucket)]
    (dicts/persist-bucket dict-name new-item)
    (assoc-in db [:dynamic :bucketed-dictionary :words word-pos] new-item)))

(defn reset-game [db]
  (let [dict-name (get-in db [:dynamic :bucketed-dictionary :name])]
    (update-in db [:dynamic :bucketed-dictionary :words]
               (fn [words] (mapv #(let [new-item (assoc % ::turn/bucket 0)]
                                   (dicts/persist-bucket dict-name new-item)
                                   new-item)
                                words)))))


(defn update-turn [db players-answer]
  (if (not players-answer)
    (setup-turn db)
    (let [answered-word (get-in db [:turn ::turn/word])
          correct-answer (get-in db [:turn ::turn/translation])
          points (turn-points ::turn/players-answer players-answer
                              ::turn/correct-answer correct-answer
                              :options (:options db))
          new-score (+ (int points) (get-in db [:dynamic :score]))]
      (-> db
          (assoc-in [:turn ::turn/prev-turn]
                    {::turn/answered-word answered-word,
                     ::turn/players-answer players-answer,
                     ::turn/correct-answer correct-answer
                     ::turn/other-choices (get-in db [:turn ::turn/other-choices])
                     ::turn/forward? (get-in db [:turn ::turn/forward?])})
          (assoc-in [:dynamic :score] new-score)
          (update-word-score (get-in db [:turn ::turn/correct-choice]) (= players-answer correct-answer))
          setup-turn))))

