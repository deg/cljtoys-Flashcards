(ns flashcards.views
  "Catch-all for page-view code that has not yet been broken out"
  (:require
   [flashcards.db :as DB]
   [flashcards.dicts.dicts :as dicts]
   [flashcards.play-view :as play-view]
   [flashcards.string-table :refer [lstr]]
   [flashcards.utils :refer [arabic? hebrew?]]
   [re-com.core :as re-com :refer-macros [handler-fn]]
   [re-frame.core :as re-frame]
   [reagent.core :as reagent]))


;; home

(defn title []
  (let [ui (re-frame/subscribe [::DB/ui-language])
        name (re-frame/subscribe [::DB/name])
        dict (re-frame/subscribe [:option ::DB/dictionary])]
    (fn []
      [re-com/v-box
       :align :center
       :children [[re-com/title :label (lstr @ui @name) :level :level2]
                  [re-com/title :label (lstr @ui (:name (dicts/get-dictionary @dict))) :level :level3]
                  ]])))

(defn credits []
  (let [ui (re-frame/subscribe [::DB/ui-language])
        version (re-frame/subscribe [::DB/version])]
    [re-com/v-box
     :children [[re-com/title :label (str (lstr @ui :prototype) " v" @version) :level :level3]]]))

(defn option-chooser [option disabled? presentation-fn]
  (let [ui (re-frame/subscribe [::DB/ui-language])
        option-value (re-frame/subscribe [:option option])
        option-choices (re-frame/subscribe [::DB/valid-options option])
        model (reagent/atom :both)]
    (fn [option disabled?]
      (let [option-map (mapv (fn [choice]
                               (let [choice ((or presentation-fn identity) choice)
                                     id    (if (vector? choice) (first choice)  choice)
                                     label (if (vector? choice) (second choice) choice)]
                                 {:id id :label (lstr @ui label)}))
                             @option-choices)]
        [re-com/h-box
         :justify :between
         :gap "1em"
         :children [(str (lstr @ui option) ": ")
                    [re-com/single-dropdown
                     :choices option-map
                     :disabled? disabled?
                     :width "200px"
                     :model option-value
                     :on-change #(re-frame/dispatch [:set-option option %])]]]))))

(defn options []
  (let [ui (re-frame/subscribe [::DB/ui-language])
        show-choices (re-frame/subscribe [:show-choices])
        dict (re-frame/subscribe [:option ::DB/dictionary])]
    (fn []
      [re-com/v-box
       :gap "0.6em"
       :children [[re-com/title :label (lstr @ui :options) :level :level2]
                  [option-chooser ::DB/dictionary false nil]
                  [option-chooser ::DB/direction false #(vector % (str (let [dic (dicts/get-dictionary @dict)
                                                                         from (lstr @ui (:from-language dic))
                                                                         to (lstr @ui (:to-language dic))]
                                                                     (str from
                                                                          ;; [TODO] Reinstate nice arrows if can
                                                                          ;; work on Android. Or, use images
                                                                          ;; (be aware of RTL vs LTR for arrows!)
                                                                          (case %
                                                                            :new-to-known " -> " #_" \u2B05 "
                                                                            :known-to-new " <- " #_" \u2B95 "
                                                                            :both " <-> "        #_" \u2B0C ")
                                                                          to))))]
                  [option-chooser ::DB/show-choices false nil]
                  [option-chooser ::DB/num-choices (= @show-choices :free-text) nil]
                  [option-chooser ::DB/ui-language false nil]]])))


(defn link-to-about-page []
  (let [ui (re-frame/subscribe [::DB/ui-language])
        name (re-frame/subscribe [::DB/name])]
    [re-com/hyperlink-href
     :label (str (lstr @ui :about) " " (lstr @ui @name))
     :href "#/about"]))

(defn link-to-play-page []
  (let [ui (re-frame/subscribe [::DB/ui-language])]
    [re-com/hyperlink-href :label (lstr @ui :start-game) :href "#/play"]))

(defn link-to-home-page []
  (let [ui (re-frame/subscribe [::DB/ui-language])]
    [re-com/hyperlink-href :label (lstr @ui :go-to-homepage) :href "#/"]))

(defn home-panel []
  [re-com/v-box
   :align :start
   :margin "1rem"
   :gap "1em"
   :children [[options]
              [re-com/h-box
               :gap "3rem"
               :children [[link-to-play-page]
                          [link-to-about-page]]]]])


(defn play-panel []
  [re-com/v-box
   :align :center
   :margin "1em"
   :gap "1em"
   :width "100%"
   :children [[play-view/score-bar]
              [play-view/full-card]
              [re-com/h-box
               :gap "3rem"
               :children [[play-view/reset-game]
                          [link-to-home-page]
                          [link-to-about-page]]]]])


;; about

(defn paragraph [text]
  [re-com/h-box
   :width "90%"
   :padding "1rem"
   :children [text]]
  )

(defn about-panel []
  [re-com/v-box
   :gap "1em"
   :children [[paragraph [credits]]
              [paragraph "This Flashcards game was designed by Aviva Goldfarb and David Goldfarb, during the summer of 2016."]
              [paragraph [link-to-home-page]]]])


;; main

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :play-panel [] [play-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :default [] [:div])

(defn main-panel []
  (let [ui (re-frame/subscribe [::DB/ui-language])
        active-panel (re-frame/subscribe [::DB/active-panel])]
    (fn []
      [re-com/v-box
       :attr {:dir (if (= @ui :english) "ltr" "rtl")}
       :height "100%"
       :children [[title]
                  [panels @active-panel]]])))
