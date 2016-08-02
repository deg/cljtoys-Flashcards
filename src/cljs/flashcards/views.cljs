(ns flashcards.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com]))


;; home

(defn title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [re-com/v-box
       :children [[re-com/title :label (str @name) :level :level2]]])))

(defn credits []
  (let [version (re-frame/subscribe [:version])]
    (fn []
      [re-com/v-box
       :children [[re-com/title :label (str " prototype v" @version) :level :level3]
                  [re-com/title :label (str "Created by David Goldfarb and Aviva Goldfarb") :level :level3]]])))

(defn option-chooser [option]
  (let [option-value (re-frame/subscribe [:option option])
        option-choices (re-frame/subscribe [:valid-options option])]
    [re-com/v-box
     :children [(str @option-value @option-choices)]]))

(defn options []
  [re-com/v-box
   :children [[option-chooser :direction]
              [option-chooser :show-choices]
              [option-chooser :num-choices]]])

(defn score-bar []
  (let [score (re-frame/subscribe [:score])
        multiplier (re-frame/subscribe [:multiplier])]
    [re-com/v-box
     :children [(str "Score: [" @score " x" @multiplier "]")]]))

(defn card [n]
  (let [translation (re-frame/subscribe [:translation-choice n])]
    [re-com/button
     :class "fc-card rc-button btn btn-default"
     :on-click #(re-frame/dispatch [:score-answer @translation])
     :label @translation]))

(defn full-card []
  (let [word (re-frame/subscribe [:word])
        num-choices (re-frame/subscribe [:num-choices])]
    [re-com/v-box
     :class "fc-full-card"
     :children [[:div {:class "subject-word"} @word]
                [re-com/v-box
                 :class "target-words"
                 :children (let [width (if (<= @num-choices 4) 2 3)
                                 rows (partition width width nil (range @num-choices))]
                             (mapv (fn [row]
                                     [re-com/h-box
                                      :children (mapv (fn [n] [card n]) row)]) rows))]]]))

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
