(ns flashcards.logic
  "Game logic. Everything here should be pure functions"
  (:require
   [cljs.spec :as s]
   [cljs.spec.test :as stest]
   [clojure.set :as set]
   [flashcards.db :as DB]
   [flashcards.dicts.dicts :as dicts]
   [flashcards.turn :as turn]
   [flashcards.utils :as utils]
   ))

(s/check-asserts true)

(defn check-db [db]
  (s/assert ::DB/db db))

;;; [TODO] Several of these functions still generate random state. All randomness should
;;; come in via the parameters, to be explicit and controllable by the testing harness.

(defn- get-word-item [db]
  (let [word-items (get-in db [::DB/dynamic :bucketed-dictionary :words])
        num-active-buckets (get-in db [::DB/dynamic :active-buckets])
        available (filter #(< (::turn/bucket %) num-active-buckets) word-items)]
    (when-not (zero? (count available))
      (rand-nth available))))

(s/fdef get-word-item
        :args #(::DB/db (:db %))
        :fn ::turn/word-item)

(defn- get-other-word-items [db correct-word-item]
  (let [word-items (get-in db [::DB/dynamic :bucketed-dictionary :words])
        num-needed (dec (get-in db [::DB/options :num-choices]))]
    (->> word-items
         shuffle
         (remove #(= % correct-word-item))
         (take num-needed))))

;; [TODO] Refactor "choice" to "word" or "word-item" soon
(defn- turn-data [db correct-word-item other-word-items]
  (let [direction (get-in db [::DB/options :direction])
        forward? (or (= direction :new-to-known)
                     (and (= direction :both) (zero? (rand-int 2))))
        word ((if forward? ::turn/word ::turn/answer) correct-word-item)
        correct-answer ((if forward? ::turn/answer ::turn/word) correct-word-item)
        other-answers (map (if forward? ::turn/answer ::turn/word) other-word-items)
        all-answers (shuffle (conj other-answers correct-answer))]
    {::turn/word word
     ::turn/correct-word-item correct-word-item
     ::turn/other-word-items other-word-items
     ::turn/correct-answer correct-answer
     ::turn/forward? forward?
     ::turn/all-answers all-answers}))

(defn- setup-turn [db]
  (let [correct-word-item (get-word-item db)
        other-word-items (get-other-word-items db correct-word-item)]
    (check-db db)
    (check-db (assoc db ::turn/turn
                     (merge (::turn/turn db)
                            {::turn/text ""}
                            (turn-data db correct-word-item other-word-items))))))

(defn first-turn [db]
  (-> db
      ;check-db
      (assoc-in [::DB/dynamic :score] 0)
      (assoc-in [::DB/dynamic :multiplier] 1)
      (assoc-in [::DB/dynamic :bucketed-dictionary]
                (-> db (get-in [::DB/options :dictionary]) dicts/get-dictionary dicts/init-dictionary))
      (assoc-in [::DB/dynamic :active-buckets] (inc (rand-int (get-in db [::DB/options :num-buckets]))))
      (assoc ::turn/turn nil)
      setup-turn
      check-db))

(defn turn-points [& {:keys [::turn/players-answer ::turn/correct-answer ::DB/options]}]
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
  (let [num-buckets (get-in db [::DB/options :num-buckets])]
    (min (inc bucket) (dec num-buckets))))

(defn update-word-score [db word-item correct?]
  (let [dict-name (get-in db [::DB/dynamic :bucketed-dictionary :name])
        word-pos (first (keep-indexed (fn [idx x] (when (= x word-item) idx))
                                      (get-in db [::DB/dynamic :bucketed-dictionary :words])))
        bucket (partial (if correct? next-bucket prev-bucket) db)
        new-item (update word-item ::turn/bucket bucket)]
    (dicts/persist-bucket dict-name new-item)
    (assoc-in db [::DB/dynamic :bucketed-dictionary :words word-pos] new-item)))

(defn reset-game [db]
  (let [dict-name (get-in db [::DB/dynamic :bucketed-dictionary :name])]
    (update-in db [::DB/dynamic :bucketed-dictionary :words]
               (fn [words] (mapv #(let [new-item (assoc % ::turn/bucket 0)]
                                   (dicts/persist-bucket dict-name new-item)
                                   new-item)
                                words)))))


(defn update-turn [db players-answer]
  (check-db db)
  (if (not players-answer)
    (setup-turn db)
    (let [word (get-in db [::turn/turn ::turn/word])
          correct-answer (get-in db [::turn/turn ::turn/correct-answer])
          points (turn-points ::turn/players-answer players-answer
                              ::turn/correct-answer correct-answer
                              ::DB/options (::DB/options db))
          new-score (+ (int points) (get-in db [::DB/dynamic :score]))]
      (-> db
          (assoc-in [::turn/turn ::turn/prev-turn]
                    {::turn/word word,
                     ::turn/players-answer players-answer,
                     ::turn/correct-answer correct-answer
                     ::turn/other-word-items (get-in db [::turn/turn ::turn/other-word-items])
                     ::turn/forward? (get-in db [::turn/turn ::turn/forward?])})
          (assoc-in [::DB/dynamic :score] new-score)
          (update-word-score (get-in db [::turn/turn ::turn/correct-word-item]) (= players-answer correct-answer))
          setup-turn))))

