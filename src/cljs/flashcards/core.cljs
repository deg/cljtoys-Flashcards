(ns flashcards.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [devtools.core :as devtools]
              [flashcards.handlers]
              [flashcards.subs]
              [flashcards.routes :as routes]
              [flashcards.views :as views]
              [flashcards.config :as config]))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")
    (devtools/set-pref! :install-sanity-hints true) ;; See https://github.com/binaryage/cljs-devtools/releases/tag/v0.4.0
    (devtools/install!)))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
