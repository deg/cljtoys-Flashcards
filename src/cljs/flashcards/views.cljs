(ns flashcards.views
    (:require [re-frame.core :as re-frame]))

(defn main-panel []
  (let [name (re-frame/subscribe [:name])
        version (re-frame/subscribe [:version])]
    (fn []
      [:div
       [:div {:class "header"}
        @name " protototype v" @version]
       [:div {:class "subheader"}
        "Created by David Goldfarb and Aviva Goldfarb"]
       [:div {:class "card-frame"}
        "Game goes here"]
       ])))
