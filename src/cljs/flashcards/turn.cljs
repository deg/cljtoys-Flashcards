(ns flashcards.turn
  "Turn data structure"
  (:require [cljs.spec :as s]))

(s/def ::direction #{:forward :reverse})

(s/def ::word (s/nilable string?)) ;; [TODO] nilable because currently is nil if no words are in the chosen buckets. Clean up soon!
(s/def ::forward boolean?)

(s/def ::word-item (s/keys :req [::word ::answer ::bucket]))
(s/def ::correct-word-item ::word-item)
(s/def ::other-word-items (s/coll-of ::word-item))

(s/def ::answer string?)
(s/def ::players-answer string?)
(s/def ::correct-answer ::word)
(s/def ::translation string?)

(s/def ::translation-choices (s/coll-of string?))

(s/def ::text string?)

(s/def ::bucketed-dictionary any?)  ;; [TODO]

(s/def ::brief-turn (s/keys :req [::word
                                  ::players-answer
                                  ::correct-answer
                                  ::other-word-items
                                  ::forward?]))
(s/def ::prev-turn ::brief-turn)

(s/def ::turn (s/nilable ;; [TODO] Try to get rid of this. Just needed at startup
               (s/keys :req [::word
                             ::correct-answer
                             ::correct-word-item
                             ::other-word-items
                             ::all-answers
                             ::forward?
                             ::text]
                       :opt [::prev-turn])))
