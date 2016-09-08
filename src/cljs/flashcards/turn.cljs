(ns flashcards.turn
  "Turn data structure"
  (:require [cljs.spec :as s]))

(s/def ::word string?)
(s/def ::boolean #(= % (boolean %)))
(s/def ::direction #{:forward :reverse})

(s/def ::choice (s/keys :req [::word ::translation ::bucket]))

;(s/def ::prev-turn (s/nilable s/any)) ;; TEMP

(s/def ::turn (s/nilable
               (s/keys :req [::word
                             ::translation
                             ::correct-choice
                             ::other-choices
                             ::translation-choices
                             ::forward?
                             ::text]
                       :opt [::prev-turn])))

