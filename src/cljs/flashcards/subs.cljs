(ns flashcards.subs
  "Subscribe side of teh reagent tango"
  (:require-macros
   [reagent.ratom :refer [reaction]])
  (:require
   [flashcards.turn :as turn]
   [re-frame.core :as re-frame]))

;; [TODO] Move from reg-sub-raw to reg-sub once I can find the new re-frame docs

(defn simple-sub [key path]
  (re-frame/reg-sub-raw
   key
   (fn [db]
     (-> @db (get-in path) reaction))))

(simple-sub :name [:static :name])
(simple-sub :version [:static :version])
(simple-sub ::turn/word [:turn ::turn/word])
(simple-sub ::turn/translation-choices [:turn ::turn/translation-choices])
(simple-sub :num-choices [:options :num-choices])
(simple-sub :show-choices [:options :show-choices])
(simple-sub :active-panel [:active-panel])
(simple-sub :score [:dynamic :score])
(simple-sub :active-buckets [:dynamic :active-buckets])
(simple-sub :multiplier [:dynamic :multiplier])
(simple-sub ::turn/prev-turn [:turn ::turn/prev-turn])
(simple-sub ::turn/text [:turn ::turn/text])
(simple-sub ::turn/bucket [:turn ::turn/correct-choice ::turn/bucket])
(simple-sub :ui-language [:options :ui-language])


(re-frame/reg-sub-raw
 ::turn/translation-choice
 (fn [db [_ n]]
   (-> @db (get-in [:turn ::turn/translation-choices n]) reaction)))

(re-frame/reg-sub-raw
 :option
 (fn [db [_ option]]
   (-> @db (get-in [:options option]) reaction)))

(re-frame/reg-sub-raw
 :valid-options
 (fn [db [_ option]]
   (-> @db (get-in [:static :valid-options option]) reaction)))

(re-frame/reg-sub-raw
 :bucket-counts
 (fn [db]
   (-> @db
       (get-in [:dynamic :bucketed-dictionary :words])
       ((partial map ::turn/bucket))
       frequencies
       sort
       reaction)))

