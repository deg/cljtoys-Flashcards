(ns flashcards.css
  (:require [garden.def :refer [defstyles]]))

(let [box-shadow "1px 2px 1px 0px rgba(0,0,0,0.75)"
      card-text-color "#606060"
      card-background-color "#F0FFFF"
      card-active-color "#80FFFF"
      card-font-size "13pt"]
  (defstyles screen
    [:.fc-card
     {:color card-text-color
      :font-size card-font-size,
      :text-align "center",
      :padding "0.4em",
      :margin ".2em",
      :border-radius ".75em",
      :display "inline-block",
      :min-width "6em",
      :transition-duration "0.3s",
      }]
    [:.fc-card:active :.fc-card:hover :.fc-card:link :.fc-card:visited
     {:text-decoration "none"
      :color card-text-color}]
    [:.unpressed
     {:background-color card-background-color,
      :box-shadow box-shadow
      }]
    [:.pressed
     {:background-color card-active-color
      :box-shadow (str "inset " box-shadow)
      }]


    [:.subject-word
     {:font-size "28pt"}]))
