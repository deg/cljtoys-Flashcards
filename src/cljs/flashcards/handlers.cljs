(ns flashcards.handlers
  "Handler side of the reagent tango"
  (:require
   [alandipert.storage-atom :refer [local-storage]]
   [flashcards.db :as DB]
   [flashcards.db-config :refer [default-db]]
   [flashcards.logic :refer [first-turn reset-game update-turn]]
   [re-frame.core :as re-frame]))


;; [TODO] This stateful bit should be isolated better, and not be left in this file.
(def persistent-options (local-storage (atom {}) :flashcard-options))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   (let [db default-db
         ;; [TODO] For this and other side-effect sensitive handlers, see
         ;; https://github.com/Day8/re-frame/blob/master/docs/EffectfulHandlers.md and
         ;; https://github.com/Day8/re-frame/blob/master/docs/Effects.md
         ;; and learn how to do this right now with reg-event-fx
         options (merge (::DB/options db) @persistent-options)]
     (assoc db ::DB/options options))))

(re-frame/reg-event-db
 :set-option
 (fn [db [_ option value]]
   ;; [TODO] Another side-effecting handler here
   (swap! persistent-options assoc option value)
   (assoc-in db [::DB/options option] value)))

(re-frame/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (let [db (assoc db :active-panel active-panel)]
     (if (= active-panel :play-panel)
       (first-turn db)
       db))))

(re-frame/reg-event-db
 :reset-game
 (fn [db _]
   (reset-game db)))

(re-frame/reg-event-db
 :score-answer
 (fn [db [_ players-answer]]
   (update-turn db (clojure.string/trim players-answer))))

(re-frame/reg-event-db
 :set-active-buckets
 (fn [db [_ n]]
   (-> db
       (assoc-in [::DB/dynamic ::DB/active-buckets] n)
       (update-turn nil))))
