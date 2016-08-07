(ns flashcards.handlers
    (:require [re-frame.core :as re-frame]
              [flashcards.db :as db]
              [flashcards.logic :refer [get-choices first-turn next-turn turn-points]]))

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
   (let [players-answer (clojure.string/trim players-answer)
         answered-word (get-in db [:turn :word])
         correct-answer (get-in db [:turn :translation])
         points (turn-points :players-answer players-answer
                             :correct-answer correct-answer
                             :options (:options db))
         new-score (+ (int points) (get-in db [:dynamic :score]))]
     (-> db
         (assoc-in [:turn :last-answer]
                   {:answered-word answered-word, :players-answer players-answer, :correct-answer correct-answer})
         (assoc-in [:dynamic :score] new-score)
         next-turn))))
