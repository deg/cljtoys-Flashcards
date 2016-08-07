(ns flashcards.logic (:require))

(defn init-game [db]
  (-> db
      (assoc-in [:dynamic :score] 0)
      (assoc-in [:dynamic :multiplier] 1)))

(defn get-choices [db]
  (let [num-choices (get-in db [:options :num-choices])
        word-pairs (->> db :dictionary shuffle (take num-choices))
        direction (get-in db [:options :direction])]
    (if (or (= direction :new-to-known)
            (and (= direction :both) (-> 2 rand-int zero?)))
      word-pairs
      (into [] (clojure.set/map-invert word-pairs)))))



(defn next-turn [db]
  (let [[[word trans1] & other-pairs] (get-choices db)
        other-trans (map second other-pairs)
        translations (shuffle (conj other-trans trans1))]
    (-> db
        (assoc-in [:turn] {:word word
                           :translation trans1
                           :translation-choices translations
                           :last-answer (get-in db [:turn :last-answer])
                           :text ""}))))

(defn first-turn [db]
  (-> db init-game next-turn))

(defn turn-points [& {:keys [players-answer correct-answer options]}]
  (let [base-wrong -10
        base-right 10
        free-text? (= :free-text (:show-choices options))
        num-choices (:num-choices options)
        choices-multiplier (if (< num-choices 5) 1.0 (- num-choices 3.5))
        direction-multiplier (case (:direction options)
                               :new-to-known 1.0
                               :known-to-new 1.6
                               :both 2.0)]
    (if (= players-answer correct-answer)
      (if free-text? 100 (* base-right direction-multiplier choices-multiplier))
      (if free-text? -10 (/ base-wrong direction-multiplier)))
    ))

