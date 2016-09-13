(ns flashcards.db-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [flashcards.db :as DB]
            [flashcards.db-config :refer [default-db]]
            [flashcards.dicts.dicts :as dicts]
            [clojure.set :as set]))

(deftest check-default-db
  (testing "has db"
    (is default-db)
    (is (::DB/static default-db))
    (is (::DB/options default-db))
    (is (::DB/dynamic default-db))))

(deftest check-db-version
  (is (get-in default-db [::DB/static ::DB/name]))
  (let [version (get-in default-db [::DB/static ::DB/version])]
    (is version)
    (is (re-matches #"\d+\.\d+\.\d+" version))))

(deftest check-db-options
  (let [known-options [::DB/dictionary ::DB/direction ::DB/num-choices ::DB/show-choices ::DB/ui-language ::DB/num-buckets]
        options (get-in default-db [::DB/options])
        valid-options (get-in default-db [::DB/static ::DB/valid-options])]
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
      (let [buckets (::DB/num-buckets dict)]
        (is (integer? buckets))
        (is (>= buckets 1))
        (is (<= buckets (get-in default-db [::DB/options ::DB/num-buckets])))))
    (testing "big enough"
      (is (> (count (:words dict)) (get-in default-db [::DB/options ::DB/num-choices]))))))
