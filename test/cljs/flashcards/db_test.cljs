(ns flashcards.db-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [flashcards.db :as db]
            [clojure.set :as set]))

(let [the-db db/default-db]

  (deftest check-default-db
    (testing "has db"
      (is the-db)
      (is (:static the-db))
      (is (:options the-db))
      (is (:dynamic the-db))
      (is (:dictionary the-db))))

  (deftest check-db-version
    (is (get-in the-db [:static :name]))
    (let [version (get-in the-db [:static :version])]
      (is version)
      (is (re-matches #"\d+\.\d+\.\d+" version))))

  (deftest check-db-options
    (let [known-options [:direction :num-choices :show-choices :interface-language]
          options (get-in the-db [:options])
          valid-options (get-in the-db [:static :valid-options])]
      (testing "has options"
        (is options)
        (is valid-options))
      (let [known-set (set known-options)
            options-set (set (keys options))
            valid-options-set (set (keys valid-options))]
        (testing "has only valid options"
          (is (empty? (set/difference options-set known-set)))
          (is (empty? (set/difference valid-options-set known-set))))
        (testing "has all valid options"
          (is (empty? (set/difference known-set options-set)))
          (is (empty? (set/difference known-set valid-options-set))))
        (testing "Is option default valid"
          (doseq [[option value] options]
            (testing option
              (is (some #{value} (option valid-options)))))))))

  (deftest check-db-dictionary
    (let [dictionary (get-in the-db [:dictionary])]
      (testing "big enough"
        (is (> (count dictionary) (get-in the-db [:options :num-choices]))))))

  )
