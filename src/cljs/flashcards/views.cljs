(ns flashcards.views
  (:require [re-frame.core :as re-frame]))

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

(defn main-panel []
  (let [name (re-frame/subscribe [:name])
        version (re-frame/subscribe [:version])]
    (fn []
      [:div
       [:div {:class "header"}
        @name " protototype v" @version]
       [:div {:class "subheader"}
        "Created by David Goldfarb and Aviva Goldfarb"]
       [full-card]])))
