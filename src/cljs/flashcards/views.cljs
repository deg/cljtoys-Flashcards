(ns flashcards.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com :refer-macros [handler-fn]]
              [reagent.core :as reagent]))


;; home

(defn title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [re-com/v-box
       :width "90%"
       :children [[re-com/h-box
                   :justify :center
                   :children [[re-com/title :label (str @name) :level :level2]]]]])))

(defn credits []
  (let [version (re-frame/subscribe [:version])]
    [re-com/v-box
     :children [[re-com/title :label (str " prototype v" @version) :level :level3]
                [re-com/title :label (str "Created by David Goldfarb and Aviva Goldfarb") :level :level3]]]))

(defn str_ [x]
  (if (keyword? x)
    (name x)
    (str x)))

(defn option-chooser [option]
  (let [option-value (re-frame/subscribe [:option option])
        option-choices (re-frame/subscribe [:valid-options option])
        model (reagent/atom :both)]
    (fn [option]
      (let [option-map (mapv (fn [choice] {:id choice :label (str_ choice)})
                             @option-choices)]
        [re-com/h-box
         :justify :between
         :gap "1em"
         :children [(str (str_ option) ": ")
                    [re-com/single-dropdown
                     :choices option-map
                     :width "200px"
                     :model option-value
                     :on-change #(re-frame/dispatch [:set-option option %])]]]))))

(defn options []
  (let [show-choices (re-frame/subscribe [:show-choices])]
    [re-com/v-box
     :gap "0.6em"
     :children [[option-chooser :direction]
                [option-chooser :show-choices]
                (when (= @show-choices :multiple-choice)
                  [option-chooser :num-choices])
                [option-chooser :interface-language]]]))

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
     :class "subject-word arabic"
     :justify :center
     :min-height "1.5em"
     :children [@word]]))

(defn card [n]
  (let [translation (re-frame/subscribe [:translation-choice n])]
    (fn [n]
      [re-com/hyperlink
       :class "fc-card unpressed"
       :on-click #(re-frame/dispatch [:score-answer @translation :allow-partial false])
       ;; [TODO] This pressed/unpressed nonsense is here because I couldn't get
       ;; buttons or links to behave right otherwise on touch screens. In all
       ;; other attempts, the button would remain highlighted after being acted upon.
       :attr  {:on-mouse-down #(set! (.-className (.-target %)) "fc-card pressed")
               :on-mouse-up #(set! (.-className (.-target %)) "fc-card unpressed")}
       :label @translation]
      )))

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

(defn link-to-about-page []
  [re-com/hyperlink-href
   :label "(About)"
   :href "#/about"])

(defn link-to-play-page []
  [re-com/hyperlink-href
   :label "Start game"
   :href "#/play"])

(defn link-to-home-page []
  [re-com/hyperlink-href
   :label "go to Home Page"
   :href "#/"])

(defn home-panel []
  [re-com/v-box
   :align :start
   :margin "1em"
   :gap "1em"
   :children [[title]
              [credits]
              [options]
              [link-to-play-page]
              [link-to-about-page]]])


(defn play-panel []
  [re-com/v-box
   :align :start
   :margin "1em"
   :gap "1em"
   :width "100%"
   :children [[title]
              [score-bar]
              [full-card]
              [link-to-home-page]
              [link-to-about-page]]])


;; about

(defn about-title []
  [re-com/title
   :label "This is the About Page."
   :level :level1])

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[about-title]
              [link-to-home-page]]])


;; main

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :play-panel [] [play-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :default [] [:div])

(defn show-panel
  [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :children [[panels @active-panel]]])))
