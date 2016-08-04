(ns flashcards.css
  (:require [garden.def :refer [defstyles]]))

(let [box-shadow "1px 2px 1px 0px rgba(0,0,0,0.75)"
      card-color "#F0FFFF"
      card-active-color "#80FFFF"
      card-font-size "13pt"]
  (defstyles screen
    [:.fc-card
     {:padding "0.4em",
      :margin ".2em",
      :border-radius ".75em",
      :display "inline-block",
      :min-width "6em",
      :font-size card-font-size,
      :background-color card-color,
      :transition-duration "0.3s",
      :box-shadow box-shadow}]
    [:.fc-card:active
     {:background-color card-active-color
      :box-shadow (str "inset box-shadow")}]
    [:.fc-card:focus
     {:background-color card-color
      :outline "none"}]
    [:.fc-card:hover
     {:background-color card-active-color}]

    [:.subject-word
     {:font-size "28pt"}]))
