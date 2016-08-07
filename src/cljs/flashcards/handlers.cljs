(ns flashcards.handlers
    (:require [re-frame.core :as re-frame]
              [flashcards.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

;;(def arabic-chars (map char (concat (range 0x0600 0x06FF) (range 0x0750 0x077F) (range 0x08A0 0x08FF)))))

(defn arabic-char? [char]
  (let [char-code (.charCodeAt char 0)]
    (or (and (<= 0x0600 char-code) (<= char-code 0x06FF))
        (and (<= 0x0750 char-code) (<= char-code 0x077F))
        (and (<= 0x08a0 char-code) (<= char-code 0x08FF)))))

(defn hebrew-char? [char]
  (let [char-code (.charCodeAt char 0)]
    (and (<= 0x00590 char-code) (<= char-code 0x05FF))))

(defn arabic? [string]
  (some arabic-char? (seq string)))

(defn hebrew? [string]
  (some hebrew-char? (seq string)))

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
   (let [players-answer (clojure.string/trim players-answer)
         answered-word (get-in db [:turn :word])
         correct-answer (get-in db [:turn :translation])
         old-score (get-in db [:dynamic :score])
         new-score (+ old-score (if (= players-answer correct-answer) 30 -10))]
     (-> db
         (assoc-in [:turn :last-answer]
                   {:answered-word answered-word, :players-answer players-answer, :correct-answer correct-answer})
         (assoc-in [:dynamic :score] new-score)
         next-turn))))
