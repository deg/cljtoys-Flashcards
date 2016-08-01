(ns flashcards.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com]))


;; home

(defn home-title []
  (let [name (re-frame/subscribe [:name])
        version (re-frame/subscribe [:version])]
    (fn []
      [re-com/v-box
       :children [[re-com/title
                   :label (str "Hello from " @name " prototype v" @version)
                   :level :level2]
                  [re-com/title
                   :label (str "Created by David Goldfarb and Aviva Goldfarb")
                   :level :level3]]])))

(defn full-card []
  (let [word (re-frame/subscribe [:word])
        choices (re-frame/subscribe [:choices])]
    [:div {:class "card"}
     [:div {:class "subject-word"} @word]
     [:div {:class "target-words"}
      "choice 1: " (get @choices 0)
      "; choice 2: " (get @choices 1)
      "; choice 3: " (get @choices 2)
      "; choice 4: " (get @choices 3)
      ]]))

(defn link-to-about-page []
  [re-com/hyperlink-href
   :label "go to About Page"
   :href "#/about"])

(defn home-panel []
  [re-com/v-box
   :gap "1em"
   :children [[home-title] [full-card] [link-to-about-page]]])


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
