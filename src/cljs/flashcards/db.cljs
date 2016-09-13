(ns flashcards.db
  "Default app state"
  (:require
   [cljs.spec :as s]
   [flashcards.turn :as turn]
   ))


(s/check-asserts true)

(s/def ::db (s/keys :req [::static ::options ::dynamic] :opt [::turn/turn]))

(s/def ::static (s/keys :req [::name ::version ::valid-options]))
(s/def ::options (s/keys :req [::dictionary ::ui-language ::direction ::num-choices ::show-choices ::num-buckets]))
(s/def ::dynamic (s/keys :req [::score ::multiplier ::bucketed-dictionary]))

(s/def ::name keyword?)
(s/def ::version string?)
(s/def ::valid-options ::options)

(s/def ::score int?)
(s/def ::multiplier int?)
(s/def ::bucketed-dictionary (s/coll-of ::turn/word-item))


