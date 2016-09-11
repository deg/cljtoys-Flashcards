(ns flashcards.db-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [flashcards.db :as DB]
            [flashcards.dicts.dicts :as dicts]
            [clojure.set :as set]))

(let [the-db DB/default-db]

  (deftest check-default-db
    (testing "has db"
      (is the-db)
      (is (::DB/static the-db))
      (is (::DB/options the-db))
      (is (::DB/dynamic the-db))))

  (deftest check-db-version
    (is (get-in the-db [::DB/static :name]))
    (let [version (get-in the-db [::DB/static :version])]
      (is version)
      (is (re-matches #"\d+\.\d+\.\d+" version))))

  (deftest check-db-options
    (let [known-options [:dictionary :direction :num-choices :show-choices :ui-language :num-buckets]
          options (get-in the-db [::DB/options])
          valid-options (get-in the-db [::DB/static :valid-options])]
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
              (let [valids (option valid-options)
                    valid-ids (map #(if (vector? %) (first %) %) valids)]
                (is (some #{value} valid-ids)))))))))

  (deftest check-db-dictionary
    (doseq [dict (vals dicts/all-dictionaries)]
      (testing "has name"
        (is (keyword? (:name dict))))
      (testing "has from language"
        (is (keyword? (:from-language dict))))
      (testing "has to language"
        (is (keyword? (:to-language dict))))
      (testing "has different languages"
        (is (not= (:from-language dict) (:to-language dict))))
      (testing "sane number of buckets"
        (let [buckets (:num-buckets dict)]
          (is (integer? buckets))
          (is (>= buckets 1))
          (is (<= buckets (get-in the-db [::DB/options :num-buckets])))))
      (testing "big enough"
        (is (> (count (:words dict)) (get-in the-db [::DB/options :num-choices])))) )))
