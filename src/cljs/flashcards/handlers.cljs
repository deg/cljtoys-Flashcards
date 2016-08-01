(ns flashcards.handlers
    (:require [re-frame.core :as re-frame]
              [flashcards.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/register-handler
 :choose-next-word
 (fn [db [_ _]]
   (let [dictionary (:dictionary db)
         [[word trans1] [_ trans2] [_ trans3] [_ trans4]] (take 4 (shuffle dictionary))
         translations (shuffle [trans1 trans2 trans3 trans4])]
     (assoc-in (assoc-in db [:dynamic :word] word)
               [:dynamic :choices] translations))))
