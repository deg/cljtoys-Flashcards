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

(simple-sub ::DB/name [::DB/static ::DB/name])
(simple-sub ::DB/version [:DB/static ::DB/version])
(simple-sub ::turn/word [::turn/turn ::turn/word])
(simple-sub ::DB/num-choices [::DB/options ::DB/num-choices])
(simple-sub :show-choices [::DB/options ::DB/show-choices])
(simple-sub ::DB/active-panel [::DB/active-panel])
(simple-sub ::DB/score [::DB/dynamic ::DB/score])
(simple-sub ::DB/active-buckets [::DB/dynamic ::DB/active-buckets])
(simple-sub ::DB/multiplier [::DB/dynamic ::DB/multiplier])
(simple-sub ::turn/prev-turn [::turn/turn ::turn/prev-turn])
(simple-sub ::turn/players-answer [::turn/turn ::turn/players-answer])
(simple-sub ::turn/bucket [::turn/turn ::turn/correct-word-item ::turn/bucket])
(simple-sub ::DB/ui-language [::DB/options ::DB/ui-language])


;; (re-frame/reg-sub-raw
;;  ::DB/all
;;  (fn [db [_ n]]
;;    (-> @db reaction)))

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
 ::DB/valid-options
 (fn [db [_ option]]
   (-> @db (get-in [::DB/static ::DB/valid-options option]) reaction)))

(re-frame/reg-sub-raw
 :bucket-counts
 (fn [db]
   (-> @db
       (get-in [::DB/dynamic :bucketed-dictionary :words])
       ((partial map ::turn/bucket))
       frequencies
       sort
       reaction)))

