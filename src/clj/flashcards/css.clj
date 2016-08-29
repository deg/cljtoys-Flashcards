(ns flashcards.css
  (:require [garden.def :refer [defstyles]]))

(defn fpct [f]
  (str (int (* f 100)) "%"))

(let [box-shadow "1px 2px 1px 0px rgba(0,0,0,0.75)"
      card-text-color "#606060"
      card-background-color "#F0FFFF"
      card-active-color "#80FFFF"
      card-font-size 1.1
      subject-font-size 2.5
      arabic-multiplier 1.3]
  (defstyles screen
    [:.fc-card
     {:color card-text-color
      :font-size (fpct card-font-size),
      :text-align "center",
      :padding "0.4rem",
      :margin ".2rem",
      :border-radius ".75rem",
      :display "inline-block",
      :min-width "11rem",
      :transition-duration "0.3s",
      }]
    [:.fc-card.arabic
     {:font-size (fpct (* card-font-size arabic-multiplier))}
     ]
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
     {:font-size (fpct subject-font-size)}]
    [:.subject-word.arabic
     {:font-size (fpct (* subject-font-size arabic-multiplier))}]

    [:.ltr-span
     {:direction "ltr"
      :display "inline-block"}]))
