(ns flashcards.logic
  "Game logic. Everything here should be pure functions"
  (:require
   [clojure.set :as set]
   [flashcards.dicts.dicts :as dicts]
   [flashcards.utils :as utils]
   ))

;;; [TODO] Several of these functions still generate random state. All randomness should
;;; come in via the parameters, to be explicit and controllable by the testing harness.

(defn- init-game [db]
  (-> db
      (assoc-in [:dynamic :score] 0)
      (assoc-in [:dynamic :multiplier] 1)
      (assoc-in [:dynamic :bucketed-dictionary]
                (-> db (get-in [:options :dictionary]) dicts/get-dictionary dicts/init-dictionary))
      (assoc-in [:dynamic :active-buckets] (inc (rand-int (get-in db [:options :num-buckets]))))
      (assoc-in [:turn] nil)))

(defn- get-word [db]
  (let [word-items (get-in db [:dynamic :bucketed-dictionary :words])
        num-active-buckets (get-in db [:dynamic :active-buckets])
        available (filter #(< (:bucket %) num-active-buckets) word-items)]
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
        word ((if forward? :word :translation) correct-choice)
        translation ((if forward? :translation :word) correct-choice)
        other-translations (map (if forward? :translation :word) other-choices)
        translation-choices (shuffle (conj other-translations translation))]
    {:word word
     :correct-choice correct-choice
     :other-choices other-choices
     :translation translation
     :forward? forward?
     :translation-choices translation-choices}))

(defn- setup-turn [db]
  (let [correct-word (get-word db)
        other-words (get-other-words db correct-word)]
    (assoc db :turn
           (merge (:turn db)
                  {:text ""}
                  (turn-data db correct-word other-words)))))

(defn first-turn [db]
  (-> db init-game setup-turn))

(defn turn-points [& {:keys [players-answer correct-answer options]}]
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
        new-item (update word-item :bucket bucket)]
    (dicts/persist-bucket dict-name new-item)
    (assoc-in db [:dynamic :bucketed-dictionary :words word-pos] new-item)))

(defn update-turn [db players-answer]
  (if (not players-answer)
    (setup-turn db)
    (let [answered-word (get-in db [:turn :word])
          correct-answer (get-in db [:turn :translation])
          points (turn-points :players-answer players-answer
                              :correct-answer correct-answer
                              :options (:options db))
          new-score (+ (int points) (get-in db [:dynamic :score]))]
      (-> db
          (assoc-in [:turn :prev-turn]
                    {:answered-word answered-word,
                     :players-answer players-answer,
                     :correct-answer correct-answer
                     :other-choices (get-in db [:turn :other-choices])
                     :forward? (get-in db [:turn :forward?])})
          (assoc-in [:dynamic :score] new-score)
          (update-word-score (get-in db [:turn :correct-choice]) (= players-answer correct-answer))
          setup-turn))))

