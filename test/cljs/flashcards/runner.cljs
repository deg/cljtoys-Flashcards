(ns flashcards.runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [flashcards.core-test]
   [flashcards.db-test]
   [flashcards.dicts-test]
   [flashcards.handlers-test]
   [flashcards.logic-test]
   [flashcards.utils-test]
   ))

(doo-tests 'flashcards.core-test
           'flashcards.utils-test
           'flashcards.db-test
           'flashcards.dicts-test
           'flashcards.logic-test
           'flashcards.handlers-test)
