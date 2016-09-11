(ns flashcards.turn
  "Turn data structure"
  (:require [cljs.spec :as s]))

(s/def ::word (s/nilable string?)) ;; [TODO] nilable because currently is nil if no words are in the chosen buckets. Clean up soon!
(s/def ::boolean #(= % (boolean %)))
(s/def ::direction #{:forward :reverse})

(s/def ::choice (s/keys :req [::word ::translation ::bucket]))

(s/def ::prev-turn (s/keys :req [::answered-word
                                 ::players-answer
                                 ::correct-answer
                                 ::other-choices
                                 ::forward?]))

(s/def ::turn (s/nilable
               (s/keys :req [::word
                             ::translation
                             ::correct-choice
                             ::other-choices
                             ::translation-choices
                             ::forward?
                             ::text]
                       :opt [::prev-turn])))

