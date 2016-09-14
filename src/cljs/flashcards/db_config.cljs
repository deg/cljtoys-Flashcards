(ns flashcards.db-config
  "Default app state"
  (:require
   [cljs.spec :as s]
   [flashcards.db :as DB]
   [flashcards.dicts.dicts :as dicts]
   ))


(s/check-asserts true)

(def default-db
  (s/assert ::DB/db
            {::DB/static {::DB/name :flashcards
                          ::DB/version "0.0.1"
                          ::DB/valid-options {::DB/dictionary (vec (dicts/get-names))
                                              ::DB/direction [:new-to-known :known-to-new :both]
                                              ::DB/show-choices [:multiple-choice :free-text]
                                              ::DB/num-choices (range 2 13)
                                              ::DB/ui-language [:english :hebrew]
                                              ::DB/num-buckets (range 3 10)}}
             ::DB/options {::DB/dictionary :arabic-grade11
                           ::DB/ui-language :english
                           ::DB/direction :new-to-known
                           ::DB/num-choices 4
                           ::DB/show-choices :multiple-choice
                           ::DB/num-buckets 5
                           }
             ::DB/dynamic {::DB/score 0
                           ::DB/multiplier 1
                           ::DB/bucketed-dictionary {}
                           }}))

