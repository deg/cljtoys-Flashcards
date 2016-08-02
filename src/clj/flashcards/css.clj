(ns flashcards.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
  [:.fc-card {:padding "2em" :margin "3px"}]
  [:body {:color "red"}]
  [:.level1 {:color "green"}])
