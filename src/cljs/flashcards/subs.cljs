(ns flashcards.subs
  "Subscribe side of teh reagent tango"
  (:require-macros
   [reagent.ratom :refer [reaction]])
  (:require
   [re-frame.core :as re-frame]))

(defn simple-sub [key path]
  (re-frame/register-sub
   key
   (fn [db]
     (-> @db (get-in path) reaction))))

(simple-sub :name [:static :name])
(simple-sub :version [:static :version])
(simple-sub :word [:turn :word])
(simple-sub :translation-choices [:turn :translation-choices])
(simple-sub :num-choices [:options :num-choices])
(simple-sub :show-choices [:options :show-choices])
(simple-sub :active-panel [:active-panel])
(simple-sub :score [:dynamic :score])
(simple-sub :active-buckets [:dynamic :active-buckets])
(simple-sub :multiplier [:dynamic :multiplier])
(simple-sub :prev-turn [:turn :prev-turn])
(simple-sub :text [:turn :text])
(simple-sub :bucket [:turn :correct-choice :bucket])
(simple-sub :ui-language [:options :ui-language])


(re-frame/register-sub
 :translation-choice
 (fn [db [_ n]]
   (-> @db (get-in [:turn :translation-choices n]) reaction)))

(re-frame/register-sub
 :option
 (fn [db [_ option]]
   (-> @db (get-in [:options option]) reaction)))

(re-frame/register-sub
 :valid-options
 (fn [db [_ option]]
   (-> @db (get-in [:static :valid-options option]) reaction)))

(re-frame/register-sub
 :bucket-counts
 (fn [db]
   (-> @db
     (get-in [:dynamic :bucketed-dictionary :words])
     ((partial map :bucket))
     frequencies
     sort
     reaction)))

