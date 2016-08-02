(ns flashcards.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [flashcards.core-test]
              [flashcards.db-test]))

(doo-tests 'flashcards.core-test
           'flashcards.db-test)
