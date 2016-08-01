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
 :choices
 (fn [db]
   (reaction (:choices (:dynamic @db)))))

(re-frame/register-sub
 :active-panel
 (fn [db _]
   (reaction (:active-panel @db))))
