(ns flashcards.handlers
    (:require [re-frame.core :as re-frame]
              [flashcards.db :as db]
              [flashcards.logic :refer [first-turn update-turn]]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
 :set-option
 (fn [db [_ option value]]
   (assoc-in db [:options option] value)))

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (let [db (assoc db :active-panel active-panel)]
     (if (= active-panel :play-panel)
       (first-turn db)
       db))))

(re-frame/register-handler
 :score-answer
 (fn [db [_ players-answer]]
   (update-turn db (clojure.string/trim players-answer))))
