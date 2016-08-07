(ns flashcards.play-view
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com :refer-macros [handler-fn]]
              [reagent.core :as reagent]
              [flashcards.utils :refer [arabic? hebrew?]]))

(defn score-bar []
  (let [score (re-frame/subscribe [:score])
        multiplier (re-frame/subscribe [:multiplier])
        last-answer-info (re-frame/subscribe [:last-answer])]
    [re-com/v-box
     :width "90%"
     :children [[re-com/h-box
                 :justify :end
                 :children [(str "Score: [" @score " x" @multiplier "]")]]
                (re-com/h-box
                 :justify :center
                 :children [(when @last-answer-info
                              (let [{:keys [answered-word players-answer correct-answer]} @last-answer-info]
                                (if  (= players-answer correct-answer)
                                  "You are correct!"
                                  (str "Sorry, " answered-word " is [" correct-answer "] not [" players-answer "]"))))])]]))

(defn subject-word []
  (let [word (re-frame/subscribe [:word])]
    [re-com/h-box
     :class (str "subject-word" (when (arabic? @word)" arabic" ))
     :justify :center
     :min-height "1.5em"
     :children [@word]]))

(defn card [n]
  (let [translation (re-frame/subscribe [:translation-choice n])]
    (fn [n]
      ;; [TODO] This could be MUCH more concise if I knew how to add/remove a single class
      (let [base-class (str (when (arabic? @translation) "arabic ") "fc-card")
            pressed-class (str "pressed " base-class)
            unpressed-class (str "unpressed " base-class)]
        [re-com/hyperlink
         :class unpressed-class
         :on-click #(re-frame/dispatch [:score-answer @translation :allow-partial false])
         ;; [TODO] This pressed/unpressed nonsense is here because I couldn't get
         ;; buttons or links to behave right otherwise on touch screens. In all
         ;; other attempts, the button would remain highlighted after being acted upon.
         :attr  {:on-mouse-down #(set! (.-className (.-target %)) pressed-class)
                 :on-mouse-up #(set! (.-className (.-target %)) unpressed-class)}
         :label @translation]))))

(defn answer-cards []
  (let [num-choices (re-frame/subscribe [:num-choices])]
    [re-com/v-box
     :class "target-words"
     :children (let [width 2 ;(if (<= @num-choices 4) 2 3)
                     rows (partition width width nil (range @num-choices))]
                 (mapv (fn [row]
                         [re-com/h-box
                          :children (mapv (fn [n] [card n]) row)]) rows))]))

(defn full-card []
  (let [show-choices (re-frame/subscribe [:show-choices])
        text (re-frame/subscribe [:text])]
    [re-com/v-box
     :class "fc-full-card"
     :children [[subject-word]
                (if (= @show-choices :multiple-choice)
                  [answer-cards]
                  [re-com/input-text
                   :model text
                   :on-change #(do
                                 (reset! text %)
                                 (re-frame/dispatch [:score-answer % :allow-partial true]))])]]))

