(ns flashcards.logic-test
  (:require [cljs.spec :as s]
            [cljs.test :refer-macros [deftest testing is]]
            [flashcards.db :as DB]
            [flashcards.db-config :refer [default-db]]
            [flashcards.dicts.dicts :as dicts]
            [flashcards.utils :as utils]
            [flashcards.logic :as logic]
            [flashcards.turn :as turn]))

(deftest startup
  (testing "initialize db"
    (let [initialized (logic/first-turn default-db)]
      (is (= (get-in initialized [::DB/dynamic ::DB/score]) 0))
      (is (= (get-in initialized [::DB/dynamic ::DB/multiplier]) 1))
      (let [turn (get-in initialized [::turn/turn])]
        (is (= turn (s/conform ::turn/turn turn)))))))

(deftest first-turn
  (let [db (logic/first-turn default-db)
        options (::DB/options default-db)
        word-items (get-in db [::DB/dynamic :bucketed-dictionary :words])
        turn (::turn/turn db)
        word (::turn/word turn)
        correct-answer (::turn/correct-answer turn)]

    (testing "Word in dictionary"
      (is (some #{word} (map ::turn/word word-items))))

    (testing "Answer in dictionary"
      (is (some #{correct-answer} (map ::turn/answer word-items))))

    (testing "No prev-turn state at start of game"
      (is (not (::turn/prev-turn turn))))

    (testing "Correct answer matches word"
      (is correct-answer (get (into {} word-items) word)))

    (testing "Other (wrong) answers"
      (let [answers (::turn/all-answers turn)]
        (doseq [answer answers]
          (is (some #{answer} (map ::turn/answer word-items))))
        (testing "Correct number of answers"
          (is (= (count answers) (::DB/num-choices options))))
        (testing "No duplicate answers"
          (is (= (count (distinct answers)) (::DB/num-choices options))))
        (testing "Includes correct answer"
          (is (some #{correct-answer} answers)))))

    (testing "buckets"
      (let [dictionary (get-in db [::DB/options ::DB/dictionary])
            bucketed (get-in db [::DB/dynamic :bucketed-dictionary :words])
            max-buckets (get-in db [::DB/options ::DB/num-buckets])]
        (testing "structure"
          (is (= (count bucketed)
                 (count (-> dictionary dicts/get-dictionary :words)))))
        (doseq [{:keys [word answer bucket]} bucketed]
          (testing word
            (is (>= bucket 0))
            (is (< bucket max-buckets))))))

    (testing "active buckets"
      (let [active-buckets (get-in db [::DB/dynamic ::DB/active-buckets])]
        (is (integer? active-buckets))
        (is (< 0 active-buckets))
        (is (<= active-buckets (get-in db [::DB/options ::DB/num-buckets])))))))

(deftest second-game
  (let [db (-> default-db
               logic/first-turn
               (#(logic/update-turn % (get-in % [::turn/turn ::turn/correct-answer])))
               logic/first-turn)
        turn (::turn/turn db)]

    (testing "No prev-turn state at start of game"
      (is (not (::turn/prev-turn turn))))))

(deftest scores
  (let [expected "GOOD"
        wrong "BAD"
        valids (get-in default-db [::DB/static ::DB/valid-options])
        options (::DB/options default-db)
        checker (fn [answer options]
                  (logic/turn-points ::turn/correct-answer expected
                                     ::turn/players-answer answer
                                     ::DB/options options))]
    (doseq [direction (::DB/direction valids)
            num-choices (::DB/num-choices valids)
            show-choices (:show-choices valids)
            :let [these-options (assoc options
                                       ::DB/direction direction
                                       ::DB/num-choices num-choices
                                       :show-choices show-choices)]]
      (testing (str "For options " these-options)
        (is (pos? (checker expected these-options)))
        (is (neg? (checker wrong these-options)))
        (when (= show-choices :multiple-choice)
          (testing "More reward for right when more choices"
            (is (<= (checker expected these-options)
                    (checker expected (update these-options ::DB/num-choices inc)))))
          (testing "More penalty for wrong when more choices"
            (is (>= (checker wrong these-options)
                    (checker wrong (update these-options ::DB/num-choices inc))))))))))


(deftest inc-dec
  (let [db (logic/first-turn default-db)
        num-buckets (get-in db [::DB/options ::DB/num-buckets])]
    (is (= 0 (logic/prev-bucket db 0)))
    (is (= 0 (logic/prev-bucket db 1)))
    (is (= 1 (logic/prev-bucket db 2)))
    (is (= (- num-buckets 1) (logic/prev-bucket db num-buckets)))
    (is (= (- num-buckets 1) (logic/next-bucket db num-buckets)))
    (is (= (- num-buckets 1) (logic/next-bucket db (- num-buckets 1))))
    (is (= (- num-buckets 1) (logic/next-bucket db (- num-buckets 2))))
    (is (= (- num-buckets 2) (logic/next-bucket db (- num-buckets 3))))
    (is (= 1 (logic/next-bucket db 0)))))

(deftest update-board
  (let [db-before (logic/first-turn default-db)
        expected (get-in db-before [::turn/turn ::turn/correct-answer])
        wrong (some #(when (not= % expected) %) (get-in db-before [::turn/turn ::turn/all-answers]))]

    (testing "pre-conditions"
      (is (not= expected wrong)))

    (let [old-turn (::turn/turn db-before)]

      (testing "right answer"
        (let [db-after (logic/update-turn db-before expected)
              new-prev-turn (get-in db-after [::turn/turn ::turn/prev-turn])]
          (is (> (get-in db-after [::DB/dynamic ::DB/score])
                 (get-in db-before [::DB/dynamic ::DB/score])))
          (is (= (::turn/word new-prev-turn) (::turn/word old-turn)))
          (is (= (::turn/correct-answer new-prev-turn) expected))
          (is (= (::turn/players-answer new-prev-turn) expected))))

      (testing "wrong answer"
        (let [db-after (logic/update-turn db-before wrong)
              new-prev-turn (get-in db-after [::turn/turn ::turn/prev-turn])]
          (is (< (get-in db-after [::DB/dynamic ::DB/score])
                 (get-in db-before [::DB/dynamic ::DB/score])))
          (is (= (::turn/word new-prev-turn) (::turn/word old-turn)))
          (is (= (::turn/correct-answer new-prev-turn) expected))
          (is (= (::turn/players-answer new-prev-turn) wrong)))))))

