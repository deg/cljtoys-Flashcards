(ns flashcards.dicts.dicts
  (:require
   [flashcards.dicts.arabic-basic :as arabic-basic]
   [flashcards.dicts.arabic-grade11 :as arabic-grade11]
   [flashcards.dicts.state-capitals :as state-capitals]))

(def all-dictionaries
  {:arabic-basic arabic-basic/dict
   :arabic-grade11 arabic-grade11/dict
   :state-capitals state-capitals/dict})

(defn get-names []
  (into {} (for [[key dict] all-dictionaries]
             [key (:name dict)])))
