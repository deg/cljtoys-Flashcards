(ns flashcards.turn
  "Turn data structure"
  (:require [cljs.spec :as s]))

(s/def ::boolean #(= % (boolean %)))

(s/def ::direction #{:forward :reverse})

(s/def ::word (s/nilable string?)) ;; [TODO] nilable because currently is nil if no words are in the chosen buckets. Clean up soon!
(s/def ::players-answer ::word)
(s/def ::correct-answer ::word)
(s/def ::forward ::boolean)

(s/def ::word-item (s/keys :req [::word ::translation ::bucket]))
(s/def ::correct-word-item ::word-item)
(s/def ::other-word-items (s/coll-of ::word-item))

(s/def ::bucketed-dictionary any?)  ;; [TODO]

(s/def ::brief-turn (s/keys :req [::word
                                  ::players-answer
                                  ::correct-answer
                                  ::other-word-items
                                  ::forward?]))
(s/def ::prev-turn ::brief-turn)

(s/def ::turn (s/keys :req [::word
                            ::translation
                            ::correct-word-item
                            ::other-word-items
                            ::translation-choices
                            ::forward?
                            ::text]
                      :opt [::prev-turn]))
