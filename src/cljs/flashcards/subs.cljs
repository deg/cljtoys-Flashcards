(ns flashcards.subs
  "Subscribe side of teh reagent tango"
  (:require-macros
   [reagent.ratom :refer [reaction]])
  (:require
   [flashcards.db :as DB]
   [flashcards.turn :as turn]
   [re-frame.core :as re-frame]))

;; [TODO] Move from reg-sub-raw to reg-sub once I can find the new re-frame docs

(defn simple-sub [key path]
  (re-frame/reg-sub-raw
   key
   (fn [db]
     (-> @db (get-in path) reaction))))

(simple-sub :name [::DB/static :name])
(simple-sub :version [:DB/static :version])
(simple-sub ::turn/word [::turn/turn ::turn/word])
(simple-sub :num-choices [::DB/options :num-choices])
(simple-sub :show-choices [::DB/options :show-choices])
(simple-sub :active-panel [:active-panel])
(simple-sub :score [::DB/dynamic :score])
(simple-sub :active-buckets [::DB/dynamic :active-buckets])
(simple-sub :multiplier [::DB/dynamic :multiplier])
(simple-sub ::turn/prev-turn [::turn/turn ::turn/prev-turn])
(simple-sub ::turn/text [::turn/turn ::turn/text])
(simple-sub ::turn/bucket [::turn/turn ::turn/correct-choice ::turn/bucket])
(simple-sub :ui-language [::DB/options :ui-language])


;(simple-sub ::turn/all-answers [::turn/turn ::turn/all-answers])
(re-frame/reg-sub-raw
 ::turn/all-answers
 (fn [db [_ n]]
   (-> @db (get-in [::turn/turn ::turn/all-answers n]) reaction)))

(re-frame/reg-sub-raw
 :option
 (fn [db [_ option]]
   (-> @db (get-in [::DB/options option]) reaction)))

(re-frame/reg-sub-raw
 :valid-options
 (fn [db [_ option]]
   (-> @db (get-in [::DB/static :valid-options option]) reaction)))

(re-frame/reg-sub-raw
 :bucket-counts
 (fn [db]
   (-> @db
       (get-in [::DB/dynamic :bucketed-dictionary :words])
       ((partial map ::turn/bucket))
       frequencies
       sort
       reaction)))

