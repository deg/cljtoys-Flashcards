(ns flashcards.core
  "Boilerplate startup"
  (:require
   [devtools.core :as devtools]
   [flashcards.config :as config]
   [flashcards.handlers]
   [flashcards.routes :as routes]
   [flashcards.subs]
   [flashcards.turn]
   [flashcards.views :as views]
   [re-frame.core :as re-frame]
   [reagent.core :as reagent]))


(defn dev-setup []
  (when true ;;config/debug?
    (enable-console-print!)
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
