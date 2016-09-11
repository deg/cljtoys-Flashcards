(ns flashcards.play-view
  "Presentation view of the main gameplay view"
  (:require-macros [clojure.core.strint :as strint])
  (:require
   [flashcards.dicts.dicts :as dicts]
   [flashcards.string-table :refer [lstr]]
   [flashcards.turn :as turn]
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
        current-bucket (re-frame/subscribe [::turn/bucket])
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
        prev-turn (re-frame/subscribe [::turn/prev-turn])
        dict (re-frame/subscribe [:option :dictionary])]
    (re-com/h-box
     :justify :center
     :children [(when @prev-turn
                  (let [{:keys [answered-word players-answer correct-answer ::turn/other-choices]} @prev-turn
                        answer-type (:answer-type (dicts/get-dictionary @dict))]
                    (if  (= players-answer correct-answer)
                      (lstr @ui :correct-score)
                      (let [forward? (::turn/forward? @prev-turn)
                            answer-type (lstr @ui answer-type)
                            source (if forward? ::turn/word ::turn/translation)
                            dest (if forward? ::turn/translation ::turn/word)
                            word-for-players-answer (source (first (filter #(= (dest %) players-answer)
                                                                           other-choices)))
                            line1-format (if forward? :incorrect-score-forward :incorrect-score-reverse)
                            line2-format (if forward? :incorrect-score-forward-second-line :incorrect-score-reverse-second-line)]
                        [re-com/v-box
                         :children (mapv (fn [line-format] [re-com/box
                                                            :child ((lstr @ui line-format)
                                                                    answer-type answered-word correct-answer
                                                                    players-answer word-for-players-answer)])
                                         [line1-format line2-format])]))))])))

(defn score-bar []
  [re-com/v-box
   :width "90%"
   :children [[score-bar-game-score]
              [score-bar-buckets]
              [score-bar-latest-turn]]])

(defn subject-word []
  (let [word (re-frame/subscribe [::turn/word])]
    [re-com/h-box
     :class (str "subject-word" (when (arabic? @word)" arabic" ))
     :justify :center
     :min-height "1.5em"
     :children [@word]]))

(defn card [n]
  (let [translation (re-frame/subscribe [::turn/translation-choice n])]
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
        word (re-frame/subscribe [::turn/word])
        show-choices (re-frame/subscribe [:show-choices])
        text (re-frame/subscribe [::turn/text])]
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

(defn reset-game []
  (let [ui (re-frame/subscribe [:ui-language])]
    [re-com/box
     :child [:a
             {:class "psuedo-link"
              :on-click #(re-frame/dispatch [:reset-game])}
             (lstr @ui :reset-game)]]))
