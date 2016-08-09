(ns flashcards.handlers
  (:require [re-frame.core :as re-frame]
            [alandipert.storage-atom :refer [local-storage]]
            [flashcards.db :as db]
            [flashcards.logic :refer [first-turn update-turn]]))


(def persistent-options (local-storage (atom {}) :flashcard-options))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   (let [db db/default-db
         options (merge (:options db) @persistent-options)]
     (assoc db :options options))))

(re-frame/register-handler
 :set-option
 (fn [db [_ option value]]
   (swap! persistent-options assoc option value)
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
