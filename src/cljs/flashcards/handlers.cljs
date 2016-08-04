(ns flashcards.handlers
    (:require [re-frame.core :as re-frame]
              [flashcards.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(defn next-turn [db]
  (let [dictionary (:dictionary db)
        num-choices (get-in db [:options :num-choices])
        [[word trans1] & other-pairs] (take num-choices (shuffle dictionary))
        other-trans (map second other-pairs)
        translations (shuffle (conj other-trans trans1))]
    (-> db
        (assoc-in [:dynamic :word] word)
        (assoc-in [:dynamic :translation] trans1)
        (assoc-in [:dynamic :translation-choices] translations))))

(defn init-game [db]
  (-> db
      (assoc-in [:dynamic :score] 0)
      (assoc-in [:dynamic :multiplier] 1)))

(defn first-turn [db]
  (-> db init-game next-turn))

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
   (let [answered-word (get-in db [:dynamic :word])
         correct-answer (get-in db [:dynamic :translation])
         old-score (get-in db [:dynamic :score])
         new-score (+ old-score (if (= players-answer correct-answer) 30 -10))]
     (-> db
         (assoc-in [:dynamic :last-answer]
                   {:answered-word answered-word, :players-answer players-answer, :correct-answer correct-answer})
         (assoc-in [:dynamic :score] new-score)
         next-turn))))
