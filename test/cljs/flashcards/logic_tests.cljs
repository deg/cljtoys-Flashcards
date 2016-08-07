(ns flashcards.logic-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [flashcards.db :as db]
            [flashcards.utils :as utils]
            [flashcards.logic :as logic]))

(let [the-db db/default-db
      options (:options the-db)]

  (deftest startup
    (testing "initialize db"
      (let [initialized (logic/init-game the-db)]
        (is (= (get-in initialized [:dynamic :score]) 0))
        (is (= (get-in initialized [:dynamic :multiplier]) 1)))))

  (deftest first-turn
    (let [db (logic/first-turn the-db)
          dictionary (:dictionary db)
          turn (:turn db)
          word (:word turn)
          translation (:translation turn)]
      (testing "Word in dictionary"
        (is (some #{word} (map first dictionary))))
      (testing "Translation in dictionary"
        (is (some #{translation} (map second dictionary))))
      (testing "Translation in other language"
        (not (= (utils/arabic? word) (utils/arabic? translation))))
      (testing "Translation matches word"
        (is translation (get (into {} dictionary) word)))
      (testing "Other (wrong) answers"
        (let [answers (:translation-choices turn)]
          (doseq [answer answers]
            (is (some #{answer} (map second dictionary)))
            (testing "Different language than word"
              (is (not= (utils/arabic? word) (utils/arabic? answer))))
            (testing "Same language as translation"
              (is (= (utils/arabic? translation) (utils/arabic? answer)))))
          (testing "Correct number of answers"
            (is (= (count answers) (:num-choices options))))
          (testing "No duplicate answers"
            (is (= (count (distinct answers)) (:num-choices options))))
          (testing "Includes correct answer"
            (is (some #{translation} answers)))))))

  (deftest scores
    (let [expected "GOOD"
          wrong "BAD"
          valids (get-in the-db [:static :valid-options])
          checker (fn [answer options]
                    (logic/turn-points :correct-answer expected :players-answer answer :options options))]
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
  )