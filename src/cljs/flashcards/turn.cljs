(ns flashcards.turn
  "Turn data structure"
  (:require [cljs.spec :as s]))

(s/def ::forward boolean?)

(s/def ::text string?)

(s/def ::word (s/nilable string?)) ;; [TODO] nilable because currently is nil if no words are in the chosen buckets. Clean up soon!

(s/def ::answer ::word)
(s/def ::players-answer ::word)
(s/def ::correct-answer ::word)
(s/def ::translation ::word)

(s/def ::all-answers (s/coll-of ::word))

(s/def ::word-item (s/keys :req [::word ::answer ::bucket]))
(s/def ::correct-word-item (s/nilable ::word-item))
(s/def ::other-word-items (s/coll-of ::word-item))

(s/def ::brief-turn (s/keys :req [::word
                                  ::players-answer
                                  ::correct-answer
                                  ::other-word-items
                                  ::forward?]))
(s/def ::prev-turn ::brief-turn)

(s/def ::turn (s/nilable ;; [TODO] Try to get rid of this. Just needed at startup
               (s/keys :req [::word
                             ::players-answer
                             ::correct-answer
                             ::correct-word-item
                             ::other-word-items
                             ::forward?
                             ::all-answers]
                       :opt [::prev-turn])))
