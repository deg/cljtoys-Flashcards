(ns flashcards.logic
  "Game logic. Everything here should be pure functions"
  (:require
   [cljs.spec :as s]
   [cljs.spec.test :as stest]
   [clojure.set :as set]
   [clojure.string :as str]
   [flashcards.db :as DB]
   [flashcards.dicts.dicts :as dicts]
   [flashcards.turn :as turn]
   [flashcards.utils :refer [answers= choose-weighted-n]]
   ))

(s/check-asserts true)

(defn check-db [db]
  (s/assert ::DB/db db))

;;; [TODO] Several of these functions still generate random state. All randomness should
;;; come in via the parameters, to be explicit and controllable by the testing harness.

(defn- get-word-item [db]
  (let [word-items (get-in db [::DB/dynamic :bucketed-dictionary :words])
        num-active-buckets (get-in db [::DB/dynamic ::DB/active-buckets])
        available (filter #(< (::turn/bucket %) num-active-buckets) word-items)]
    (when-not (zero? (count available))
      (rand-nth available))))

(s/fdef get-word-item
        :args #(::DB/db (:db %))
        :fn ::turn/word-item)

(defn turn-forward? [db]
  (let [direction (get-in db [::DB/options ::DB/direction])]
    (or (= direction :new-to-known)
        (and (= direction :both) (zero? (rand-int 2))))))

(defn word-similarity
  "Rank how similar two words are.  Identical words return 1.0. Very different words approach a score of 0.0"
  [word1 word2]
  (let [word1 (str/upper-case word1)
        word2 (str/upper-case word2)
        max-len (max (count word1) (count word2))
        min-len (min (count word1) (count word2))]
    (Math.pow (* (Math.pow (/ min-len max-len) 0.2)
                 (if (= (first word1) (first word2)) 1.0 0.5)
                 (if (= (second word1) (second word2)) 1.0 0.8)
                 (if (= (last word1) (last word2)) 1.0 0.7))
              2)))

(defn score-words [correct-word other-words]
  (map (juxt identity (partial word-similarity correct-word)) other-words))

(defn- get-other-word-items [db correct-word-item forward?]
  (let [word-items (get-in db [::DB/dynamic :bucketed-dictionary :words])
        num-needed (dec (get-in db [::DB/options ::DB/num-choices]))
        accessor (if forward? ::turn/answer ::turn/word)
        word (accessor correct-word-item)
        others (remove #{word} (map accessor word-items))
        with-scores (sort-by second (score-words word others))
        chosen-others (map first (choose-weighted-n num-needed with-scores second))]
    (shuffle (map (fn [word]
                    (first (filter #(= word (accessor %)) word-items)))
                  chosen-others))))

(defn- turn-data [db correct-word-item other-word-items forward?]
  (let [word ((if forward? ::turn/word ::turn/answer) correct-word-item)
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
        forward? (turn-forward? db)
        other-word-items (get-other-word-items db correct-word-item forward?)]
    (check-db db)
    (check-db (assoc db ::turn/turn
                     (merge (::turn/turn db)
                            {::turn/players-answer ""}
                            (turn-data db correct-word-item other-word-items forward?))))))

(defn first-turn [db]
  (-> db
      ;check-db
      (assoc-in [::DB/dynamic ::DB/score] 0)
      (assoc-in [::DB/dynamic ::DB/multiplier] 1)
      (assoc-in [::DB/dynamic :bucketed-dictionary]
                (-> db (get-in [::DB/options ::DB/dictionary]) dicts/get-dictionary dicts/init-dictionary))
      (assoc-in [::DB/dynamic ::DB/active-buckets] (inc (rand-int (get-in db [::DB/options ::DB/num-buckets]))))
      (assoc ::turn/turn nil)
      setup-turn
      check-db))

(defn turn-points [& {:keys [::turn/players-answer ::turn/correct-answer ::DB/options]}]
  (let [base-wrong -10
        base-right 10
        free-text? (= :free-text (:show-choices options))
        num-choices (::DB/num-choices options)
        choices-multiplier (if (< num-choices 5) 1.0 (- num-choices 3.5))
        direction-multiplier (case (::DB/direction options)
                               :new-to-known 1.0
                               :known-to-new 1.6
                               :both 2.0)]
    (if (answers= players-answer correct-answer)
      (if free-text? 100 (* base-right direction-multiplier choices-multiplier))
      (if free-text? -10 (/ base-wrong direction-multiplier)))))

(defn- prev-bucket [db bucket]
  (max (dec bucket) 0))

(defn- next-bucket [db bucket]
  (let [num-buckets (get-in db [::DB/options ::DB/num-buckets])]
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
          new-score (+ (int points) (get-in db [::DB/dynamic ::DB/score]))]
      (-> db
          (assoc-in [::turn/turn ::turn/prev-turn]
                    {::turn/word word,
                     ::turn/players-answer players-answer,
                     ::turn/correct-answer correct-answer
                     ::turn/other-word-items (get-in db [::turn/turn ::turn/other-word-items])
                     ::turn/forward? (get-in db [::turn/turn ::turn/forward?])})
          (assoc-in [::DB/dynamic ::DB/score] new-score)
          (update-word-score (get-in db [::turn/turn ::turn/correct-word-item]) (= players-answer correct-answer))
          setup-turn))))

