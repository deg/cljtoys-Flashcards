(ns flashcards.play-view
  "Presentation view of the main gameplay view"
  (:require-macros [clojure.core.strint :as strint])
  (:require
   [flashcards.string-table :refer [lstr]]
   [flashcards.utils :refer [arabic? hebrew?]]
   [goog.string :as gstring]
   [re-com.core :as re-com :refer-macros [handler-fn]]
   [re-frame.core :as re-frame]
   [reagent.core :as reagent]))

(defn score-bar-game-score []
  (let [ui (re-frame/subscribe [:ui-language])
        score (re-frame/subscribe [:score])
        multiplier (re-frame/subscribe [:multiplier])]
    [re-com/h-box
     :justify :end
     :children [(lstr @ui :score)
                ": ["
                ;; (need LTR to force minus-sign to left)
                [:span {:class "ltr-span"} (str @score)]
                (gstring/unescapeEntities "&nbsp;")
                " x"
                @multiplier
                "]"]]))

(defn score-bar-buckets []
  (let [ui (re-frame/subscribe [:ui-language])
        counts (re-frame/subscribe [:bucket-counts])
        num-buckets (re-frame/subscribe [:option :num-buckets])
        current-bucket (re-frame/subscribe [:bucket])
        active-buckets (re-frame/subscribe [:active-buckets])
        nbsp (gstring/unescapeEntities "&nbsp;")]
    [re-com/h-box
     :justify :end
     :children [(lstr @ui :buckets)
                ":"
                nbsp
                (let [count-map (into {} @counts)]
                  (into [:span]
                        (interpose nbsp
                                   (mapv (fn [n]
                                           [:span {:on-click #(re-frame/dispatch [:set-active-buckets (inc n)])
                                                   :class
                                                   (str (if (= n @current-bucket) "current-bucket" "")
                                                        " bucket "
                                                        (if (< n @active-buckets) "active-bucket" ""))}
                                            (str (get count-map n 0))])
                                         (range @num-buckets)))))]]))

(defn score-bar-latest-turn []
  (let [ui (re-frame/subscribe [:ui-language])
        prev-turn(re-frame/subscribe [:prev-turn])]
    (re-com/h-box
     :justify :center
     :children [(when @prev-turn
                  (let [{:keys [answered-word players-answer correct-answer]} @prev-turn]
                    (if  (= players-answer correct-answer)
                      (lstr @ui :correct-score)
                      ((lstr @ui :incorrect-score) answered-word correct-answer players-answer))))])))

(defn score-bar []
  [re-com/v-box
   :width "90%"
   :children [[score-bar-game-score]
              [score-bar-buckets]
              [score-bar-latest-turn]]])

(defn subject-word []
  (let [word (re-frame/subscribe [:word])]
    [re-com/h-box
     :class (str "subject-word" (when (arabic? @word)" arabic" ))
     :justify :center
     :min-height "1.5em"
     :children [@word]]))

(defn card [n]
  (let [translation (re-frame/subscribe [:translation-choice n])]
    (fn [n]
      ;; [TODO] This could be MUCH more concise if I knew how to add/remove a single class
      (let [base-class (str (when (arabic? @translation) "arabic ") "fc-card")
            pressed-class (str "pressed " base-class)
            unpressed-class (str "unpressed " base-class)]
        [re-com/hyperlink
         :class unpressed-class
         :on-click #(re-frame/dispatch [:score-answer @translation :allow-partial false])
         ;; [TODO] This pressed/unpressed nonsense is here because I couldn't get
         ;; buttons or links to behave right otherwise on touch screens. In all
         ;; other attempts, the button would remain highlighted after being acted upon.
         :attr  {:on-mouse-down #(set! (.-className (.-target %)) pressed-class)
                 :on-mouse-up #(set! (.-className (.-target %)) unpressed-class)}
         :label @translation]))))

(defn answer-cards []
  (let [num-choices (re-frame/subscribe [:num-choices])]
    [re-com/v-box
     :class "target-words"
     :children (let [width 2 ;(if (<= @num-choices 4) 2 3)
                     rows (partition width width nil (range @num-choices))]
                 (mapv (fn [row]
                         [re-com/h-box
                          :children (mapv (fn [n] [card n]) row)]) rows))]))

(defn full-card []
  (let [ui (re-frame/subscribe [:ui-language])
        word (re-frame/subscribe [:word])
        show-choices (re-frame/subscribe [:show-choices])
        text (re-frame/subscribe [:text])]
    [re-com/v-box
     :class "fc-full-card"
     :children (if (not @word)
                 [(lstr @ui :buckets-empty)]
                 [[subject-word]
                  (if (= @show-choices :multiple-choice)
                    [answer-cards]
                    [re-com/input-text
                     :model text
                     :on-change #(do
                                   (reset! text %)
                                   (re-frame/dispatch [:score-answer % :allow-partial true]))])])]))

