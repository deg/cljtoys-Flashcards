(ns flashcards.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [flashcards.core-test]
              [flashcards.utils-test]
              [flashcards.db-test]
              [flashcards.handlers-test]
              ))

(doo-tests 'flashcards.core-test
           'flashcards.utils-test
           'flashcards.db-test
           'flashcards.handlers-test)
