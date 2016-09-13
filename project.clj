(defproject flashcards "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0-alpha12"]
                 [org.clojure/clojurescript "1.9.229"]
                 [org.clojure/core.incubator "0.1.4"]
                 [reagent "0.6.0-rc"]
                 [binaryage/devtools "0.8.1"]
                 [re-frame "0.8.0"]
                 [re-com "0.8.3"]
                 [secretary "1.2.3"]
                 [garden "1.3.2"]
                 [ns-tracker "0.3.0"]
                 [compojure "1.5.0"]
                 [yogthos/config "0.8"]
                 [ring "1.4.0"]

                 ;; [TOOD] Figure out how to move this into dev profile, without production building barfing on reference
                 [data-frisk-reagent "0.2.6"]

                 ;; Atom interface to local storage
                 [alandipert/storage-atom "2.0.1"]]

  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-garden "0.2.8"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"
                                    "test/js"
                                    "resources/public/css"]

  :figwheel {:css-dirs ["resources/public/css"]
             :ring-handler flashcards.handler/dev-handler}

  :garden {:builds [{:id           "screen"
                     :source-paths ["src/clj"]
                     :stylesheet   flashcards.css/screen
                     :compiler     {:output-to     "resources/public/css/screen.css"
                                    :pretty-print? true}}]}

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :profiles
  {:dev
   {:dependencies [[figwheel-sidecar "0.5.6"]
                   [com.cemerick/piggieback "0.2.1"]
                   [org.clojure/test.check "0.9.0"]]

    :plugins      [[lein-figwheel "0.5.6"]
                   [lein-doo "0.1.6"]
                   [cider/cider-nrepl "0.14.0-SNAPSHOT"]]
    }}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "flashcards.core/mount-root"}
     :compiler     {:main                 flashcards.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true}}

    {:id           "min"
     :source-paths ["src/cljs"]
     :jar true
     :compiler     {:main            flashcards.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :output-dir      "resources/public/js/compiled/out-min"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}
    {:id           "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler     {:output-to     "resources/public/js/compiled/test.js"
                    :output-dir    "resources/public/js/compiled/out-test"
                    :main          flashcards.runner
                    :optimizations :none}}
    ]}

  :main flashcards.server

  :aot [flashcards.server]

  :uberjar-name "flashcards.jar"

  :prep-tasks [["cljsbuild" "once" "min"]["garden" "once"] "compile"]
  )
