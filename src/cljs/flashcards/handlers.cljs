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
   (when (= active-panel :play-panel)
     (re-frame/dispatch [:choose-next-word]))
   (assoc db :active-panel active-panel)))

(re-frame/register-handler
 :score-answer
 (fn [db [_ this-answer]]
   (let [correct-answer (get-in db [:dynamic :translation])]
     (println "ANSWER: " this-answer correct-answer))
   (re-frame/dispatch [:choose-next-word])
   db))

(re-frame/register-handler
 :choose-next-word
 (fn [db _]
   (let [dictionary (:dictionary db)
         num-choices (get-in db [:options :num-choices])
         [[word trans1] & other-pairs] (take num-choices (shuffle dictionary))
         other-trans (map second other-pairs)
         translations (shuffle (conj other-trans trans1))
         db (assoc-in db [:dynamic :word] word)
         db (assoc-in db [:dynamic :translation] trans1)
         db (assoc-in db [:dynamic :translation-choices] translations)]
     db)))
