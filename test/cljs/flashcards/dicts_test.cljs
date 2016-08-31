(ns flashcards.dicts-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [flashcards.db :refer [default-db]]
            [flashcards.dicts.dicts :as dicts]
            [flashcards.dicts.state-capitals :as state-capitals]))


(deftest init-dictionary
  (testing "states"
    (let [dictionary (dicts/init-dictionary state-capitals/dict)
          max-buckets (get-in default-db [:options :num-buckets])]
      (testing "valid dictionary"
        (is dictionary)
        (is (:name dictionary))
        (is (:words dictionary)))
      (testing "words"
        (doseq [word-data (:words dictionary)]
          (is (:word word-data))
          (is (:translation word-data))
          (let [bucket (:bucket word-data)]
            (is (integer? bucket))
            (is (<= 0 bucket))
            (is (< bucket max-buckets))))))))

