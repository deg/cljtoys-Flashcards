(ns flashcards.string-table
  "Quik-n-dirty i10n translation tables"
  (:require-macros
   [clojure.core.strint :as strint]))

(def ^:private string-table
  {:about           {:english "About"              :hebrew "אודות"}
   :and-not         {:english "and not"            :hebrew "ולא"}
   :both            {:english "Both"               :hebrew "שתיהם"}
   :correct-score   {:english "You are correct!"   :hebrew "נכון!"}
   :direction       {:english "direction"          :hebrew "כיוון"}
   :english         {:english "English"            :hebrew "אנגלית"}
   :flashcards      {:english "Flashcards"         :hebrew "כרטיסיות"}
   :free-text       {:english "Free text"          :hebrew "תשובות פתוחות"}
   :go-to-homepage  {:english "Go to Home Page"    :hebrew "חזרה לדף הבית"}
   :hebrew          {:english "Hebrew"             :hebrew "עברית"}
   :known-to-new    {:english "Known to new"       :hebrew "ידוע לחדש"}
   :multiple-choice {:english "Multiple choice"    :hebrew "תשובות אמריקיות"}
   :new-to-known    {:english "New to known"       :hebrew "חדש לידוע"}
   :num-choices     {:english "Num choices"        :hebrew "מס' תשובות"}
   :options         {:english "Options"            :hebrew "אפשרויות"}
   :score           {:english "Score"              :hebrew "ניקוד"}
   :prototype       {:english "Prototype"          :hebrew "אב טיפוס"}
   :show-choices    {:english "Show choices"       :hebrew "אפשרויות"}
   :start-game      {:english "Start game"         :hebrew "התחל משחק"}
   :ui-language     {:english "Interface language" :hebrew "שפת תצוגה"}

   ;; Interpolated strings (still a bit kludgy)
   :incorrect-score
   {:english
    (fn [answered-word correct-answer players-answer]
      (strint/<< "For \"~{answered-word}\", the correct answer is \"~{correct-answer}\" and not \"~{players-answer}\"."))
    :hebrew
    (fn [answered-word correct-answer players-answer]
      (strint/<< "בשביל \"~{answered-word}\", התשובה הנכונה היא \"~{correct-answer}\" ולא \"~{players-answer}\"."))
    }
   })
		



(defn lstr [language key]
  (or (get-in string-table [key language])
      (str "???-" key "-???")) )




