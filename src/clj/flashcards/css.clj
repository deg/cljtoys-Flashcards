(ns flashcards.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:.fc-card
   {:padding "1em",
    :margin "3px",
    :border-radius "12px",
    :display "inline-block",
    :min-width "6em",
    :font-size "14pt",
    :background-color "#F0FFFF",
    :box-shadow "1px 2px 1px 0px rgba(0,0,0,0.75)"}]
  [:.fc-card:hover
   {:background-color "#80FFFF"}]
  [:.fc-card:active
   {:background-color "#80FFFF"
    :box-shadow "inset 1px 2px 1px 0px rgba(0,0,0,0.75)"}]
  [:.fc-card:focus
   {:outline "none"}]
  [:.subject-word
   {:font-size "28pt"}]
)
