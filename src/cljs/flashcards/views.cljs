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

(defn full-card []
  (let [word (re-frame/subscribe [:word])
        translation-choices (re-frame/subscribe [:translation-choices])]
    [re-com/v-box
     :class "card"
     :children [[:div {:class "subject-word"} @word]
                [re-com/v-box
                 :class "target-words"
                 :children [[re-com/h-box
                             :children [[re-com/button
                                         :label (get @translation-choices 0)]
                                        [re-com/button
                                         :label (get @translation-choices 1)]]]
                            [re-com/h-box
                             :children [[re-com/button
                                          :label (get @translation-choices 2)]
                                         [re-com/button
                                          :label (get @translation-choices 3)]]]]]]]))

(defn next-button []
  [re-com/button
   :label "Next"
   :class "fc-button"
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
