(ns flashcards.logic-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [flashcards.db :refer [default-db]]
            [flashcards.dicts.dicts :as dicts]
            [flashcards.utils :as utils]
            [flashcards.logic :as logic]))

(deftest startup
  (testing "initialize db"
    (let [initialized (logic/init-game default-db)]
      (is (= (get-in initialized [:dynamic :score]) 0))
      (is (= (get-in initialized [:dynamic :multiplier]) 1))
      (is (= (get-in initialized [:turn]) nil)))))

(deftest first-turn
  (let [db (logic/first-turn default-db)
        options (:options default-db)
        word-items (get-in db [:dynamic :bucketed-dictionary :words])
        turn (:turn db)
        word (:word turn)
        translation (:translation turn)]

    (testing "Word in dictionary"
      (is (some #{word} (map :word word-items))))

    (testing "Translation in dictionary"
      (is (some #{translation} (map :translation word-items))))

    #_(testing "Translation in other language"
      (is (not= (utils/arabic? word) (utils/arabic? translation))))

    (testing "No prev-turn state at start of game"
      (is (not (:prev-turn turn))))

    (testing "Translation matches word"
      (is translation (get (into {} word-items) word)))

    (testing "Other (wrong) answers"
      (let [answers (:translation-choices turn)]
        (doseq [answer answers]
          (is (some #{answer} (map :translation word-items)))
          #_(testing "Different language than word"
            (is (not= (utils/arabic? word) (utils/arabic? answer))))
          (testing "Same language as translation"
            (is (= (utils/arabic? translation) (utils/arabic? answer)))))
        (testing "Correct number of answers"
          (is (= (count answers) (:num-choices options))))
        (testing "No duplicate answers"
          (is (= (count (distinct answers)) (:num-choices options))))
        (testing "Includes correct answer"
          (is (some #{translation} answers)))))

    (testing "buckets"
      (let [dictionary (get-in db [:options :dictionary])
            bucketed (get-in db [:dynamic :bucketed-dictionary :words])
            max-buckets (get-in db [:options :num-buckets])]
        (testing "structure"
          (is (= (count bucketed)
                 (count (-> dictionary dicts/get-dictionary :words)))))
        (doseq [{:keys [word translation bucket]} bucketed]
          (testing word
            (is (>= bucket 0))
            (is (< bucket max-buckets))
            #_(is (not= (utils/arabic? word) (utils/arabic? translation)))
            #_(is (or (utils/arabic? word) (utils/arabic? translation)))))))

    (testing "active buckets"
      (let [active-buckets (get-in db [:dynamic :active-buckets])]
        (is (integer? active-buckets))
        (is (< 0 active-buckets))
        (is (<= active-buckets (get-in db [:options :num-buckets])))))))

(deftest second-game
  (let [db (-> default-db
               logic/first-turn
               (#(logic/update-turn % (get-in % [:turn :translation])))
               logic/first-turn)
        turn (:turn db)]

    (testing "No prev-turn state at start of game"
      (is (not (:prev-turn turn))))))

(deftest scores
  (let [expected "GOOD"
        wrong "BAD"
        valids (get-in default-db [:static :valid-options])
        options (:options default-db)
        checker (fn [answer options]
                  (logic/turn-points :correct-answer expected
                                     :players-answer answer
                                     :options options))]
    (doseq [direction (:direction valids)
            num-choices (:num-choices valids)
            show-choices (:show-choices valids)
            :let [these-options (assoc options
                                       :direction direction
                                       :num-choices num-choices
                                       :show-choices show-choices)]]
      (testing (str "For options" these-options)
        (is (pos? (checker expected these-options)))
        (is (neg? (checker wrong these-options)))
        (when (= show-choices :multiple-choice)
          (testing "More reward for right when more choices"
            (is (<= (checker expected these-options)
                    (checker expected (update these-options :num-choices inc)))))
          (testing "More penalty for wrong when more choices"
            (is (>= (checker wrong these-options)
                    (checker wrong (update these-options :num-choices inc))))))))))


(deftest inc-dec
  (let [db (logic/first-turn default-db)
        num-buckets (get-in db [:options :num-buckets])]
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
        expected (get-in db-before [:turn :translation])
        wrong (some #(when (not= % expected) %) (get-in db-before [:turn :translation-choices]))]

    (testing "pre-conditions"
      (is (not= expected wrong)))

    (let [old-turn (:turn db-before)]

      (testing "right answer"
        (let [db-after (logic/update-turn db-before expected)new-prev-turn
              (get-in db-after [:turn :prev-turn])]
          (is (> (get-in db-after [:dynamic :score])
                 (get-in db-before [:dynamic :score])))
          (is (= (:answered-word new-prev-turn) (:word old-turn)))
          (is (= (:correct-answer new-prev-turn) expected))
          (is (= (:players-answer new-prev-turn) expected))))

      (testing "wrong answer"
        (let [db-after (logic/update-turn db-before wrong)
              new-prev-turn (get-in db-after [:turn :prev-turn])]
          (is (< (get-in db-after [:dynamic :score])
                 (get-in db-before [:dynamic :score])))
          (is (= (:answered-word new-prev-turn) (:word old-turn)))
          (is (= (:correct-answer new-prev-turn) expected))
          (is (= (:players-answer new-prev-turn) wrong)))))))

