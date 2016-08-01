(ns flashcards.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [flashcards.core-test]))

(doo-tests 'flashcards.core-test)
