(ns flashcards.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com]))


;; home

(defn home-title []
  (let [name (re-frame/subscribe [:name])
        version (re-frame/subscribe [:version])]
    (fn []
      [re-com/v-box
       :children [[re-com/title :label (str @name) :level :level2]
                  [re-com/title :label (str " prototype v" @version) :level :level3]
                  [re-com/title :label (str "Created by David Goldfarb and Aviva Goldfarb") :level :level3]]])))

(defn option-chooser [option]
  (let [option-value (re-frame/subscribe [:option option])
        option-choices (re-frame/subscribe [:valid-options option])]
    [re-com/v-box
     :children [(str @option-value @option-choices)]]))

(defn card [n]
  (let [translation (re-frame/subscribe [:translation-choice n])]
    [re-com/button
     :class "fc-card rc-button btn btn-default"
     :label @translation]))

(defn full-card []
  (let [word (re-frame/subscribe [:word])
        num-choices (re-frame/subscribe [:num-choices])]
    [re-com/v-box
     :class "fc-full-card"
     :children [[option-chooser :direction]
                [option-chooser :show-choices]
                [option-chooser :num-choices]
                [:div {:class "subject-word"} @word]
                [re-com/v-box
                 :class "target-words"
                 :children (let [width (if (<= @num-choices 4) 2 3)
                                 rows (partition width width nil (range @num-choices))]
                             (mapv (fn [row]
                                     [re-com/h-box
                                      :children (mapv (fn [n] [card n]) row)]) rows))]]]))

(defn next-button []
  [re-com/button
   :label "Next"
   :class "fc-button rc-button btn btn-default"
   :on-click #(re-frame/dispatch [:choose-next-word])
   :tooltip "Score me, and go to next word"])

(defn link-to-about-page []
  [re-com/hyperlink-href
   :label "go to About Page"
   :href "#/about"])

(defn home-panel []
  [re-com/v-box
   :align :start
   :margin "1em"
   :gap "1em"
   :children [[home-title]
              [full-card]
              [next-button]
              [link-to-about-page]]])


;; about

(defn about-title []
  [re-com/title
   :label "This is the About Page."
   :level :level1])

(defn link-to-home-page []
  [re-com/hyperlink-href
   :label "go to Home Page"
   :href "#/"])

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[about-title] [link-to-home-page]]])


;; main

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
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
