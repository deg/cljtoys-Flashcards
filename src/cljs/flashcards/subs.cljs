(ns flashcards.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/register-sub
 :name
 (fn [db]
   (reaction (:name (:static @db)))))

(re-frame/register-sub
 :version
 (fn [db]
   (reaction (:version (:static @db)))))

(re-frame/register-sub
 :word
 (fn [db]
   (reaction (:word (:dynamic @db)))))

(re-frame/register-sub
 :translation-choices
 (fn [db]
   (reaction (:translation-choices (:dynamic @db)))))

(re-frame/register-sub
 :translation-choice
 (fn [db [_ n]]
   (reaction (get (:translation-choices (:dynamic @db)) n))))

(re-frame/register-sub
 :num-choices
 (fn [db _]
   (reaction (get-in @db [:options :num-choices]))))

(re-frame/register-sub
 :active-panel
 (fn [db _]
   (reaction (:active-panel @db))))
