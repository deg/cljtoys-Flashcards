(ns flashcards.db
  "Default app state"
  (:require
   [cljs.spec :as s]
   [flashcards.dicts.dicts :as dicts]
   [flashcards.turn :as turn]
   ))


(s/check-asserts true)

;; [TODO] Fill in
(s/def ::static any? #_(s/keys :req [::name ::version ::valid-options]))
(s/def ::options any?)
(s/def ::dynamic any?)
(s/def ::turn (s/nilable ::turn/turn))

(s/def ::db (s/keys :req [::static ::options ::dynamic] :opt [::turn/turn]))

(def default-db
  (s/assert ::db
            {::static {:name :flashcards
                       :version "0.0.1"
                       :valid-options {:dictionary (into [] (dicts/get-names))
                                       :direction [:new-to-known :known-to-new :both]
                                       :show-choices [:multiple-choice :free-text]
                                       :num-choices (range 2 13)
                                       :ui-language [:english :hebrew]
                                       :num-buckets (range 3 10)}}
             ::options {:dictionary :arabic-grade11
                        :ui-language :english
                        :direction :new-to-known
                        :num-choices 4
                        :show-choices :multiple-choice
                        :num-buckets 5
                        }
             ::dynamic {:score 0
                        :multiplier 1
                        :bucketed-dictionary {}
                        }}))

