(ns flashcards.dicts-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [flashcards.db :as DB :refer [default-db]]
            [flashcards.dicts.dicts :as dicts]
            [flashcards.dicts.state-capitals :as state-capitals]
            [flashcards.turn :as turn]))


(deftest init-dictionary
  (testing "states"
    (let [dictionary (dicts/init-dictionary state-capitals/dict)
          max-buckets (get-in default-db [::DB/options :num-buckets])]
      (testing "valid dictionary"
        (is dictionary)
        (is (:name dictionary))
        (is (:words dictionary)))
      (testing "words"
        (doseq [word-data (:words dictionary)]
          (is (::turn/word word-data))
          (is (::turn/translation word-data))
          (let [bucket (::turn/bucket word-data)]
            (is (integer? bucket))
            (is (<= 0 bucket))
            (is (< bucket max-buckets))))))))

