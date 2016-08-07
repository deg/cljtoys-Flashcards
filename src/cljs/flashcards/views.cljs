(ns flashcards.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com :refer-macros [handler-fn]]
              [reagent.core :as reagent]
              [flashcards.utils :refer [arabic? hebrew?]]
              [flashcards.play-view :as play-view]))


;; home

(defn title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [re-com/v-box
       :width "90%"
       :children [[re-com/h-box
                   :justify :center
                   :children [[re-com/title :label (str @name) :level :level1]]]]])))

(defn credits []
  (let [version (re-frame/subscribe [:version])]
    [re-com/v-box
     :children [[re-com/title :label (str " prototype v" @version) :level :level3]]]))

(defn str_ [x]
  (if (keyword? x)
    (name x)
    (str x)))

(defn option-chooser [option disabled?]
  (let [option-value (re-frame/subscribe [:option option])
        option-choices (re-frame/subscribe [:valid-options option])
        model (reagent/atom :both)]
    (fn [option disabled?]
      (let [option-map (mapv (fn [choice] {:id choice :label (str_ choice)})
                             @option-choices)]
        [re-com/h-box
         :justify :between
         :gap "1em"
         :children [(str (str_ option) ": ")
                    [re-com/single-dropdown
                     :choices option-map
                     :disabled? disabled?
                     :width "200px"
                     :model option-value
                     :on-change #(re-frame/dispatch [:set-option option %])]]]))))

(defn options []
  (let [show-choices (re-frame/subscribe [:show-choices])]
    (fn []
      [re-com/v-box
       :gap "0.6em"
       :children [[re-com/title :label "Options" :level :level2]
                  [option-chooser :direction false]
                  [option-chooser :show-choices false]
                  [option-chooser :num-choices (= @show-choices :free-text)]
                  [option-chooser :interface-language]]])))


(defn link-to-about-page []
  [re-com/hyperlink-href
   :label "About Flashcards"
   :href "#/about"])

(defn link-to-play-page []
  [re-com/hyperlink-href :label "Start game now" :href "#/play"])

(defn link-to-home-page []
  [re-com/hyperlink-href :label "go to Home Page" :href "#/"])

(defn home-panel []
  [re-com/v-box
   :align :start
   :margin "1rem"
   :gap "1em"
   :children [[options]
              [re-com/h-box
               :gap "3rem"
               :children [[link-to-play-page]
                          [link-to-about-page]]]]])


(defn play-panel []
  [re-com/v-box
   :align :start
   :margin "1em"
   :gap "1em"
   :width "100%"
   :children [[play-view/score-bar]
              [play-view/full-card]
              [link-to-home-page]
              [link-to-about-page]]])


;; about

(defn paragraph [text]
  [re-com/h-box
   :width "90%"
   :padding "1rem"
   :children [text]]
  )

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[paragraph [credits]]
              [paragraph "This Flashcards game was designed by Aviva Goldfarb and David Goldfarb, during the summer of 2016."]
              [paragraph [link-to-home-page]]]])


;; main

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :play-panel [] [play-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :default [] [:div])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [re-com/v-box
       :height "100%"
       :children [[title]
                  [panels @active-panel]]])))
