(ns flashcards.string-table
  "Quik-n-dirty i10n translation tables"
  (:require-macros
   [clojure.core.strint :as strint]))

(def ^:private string-table
  {:11th-grade-arabic {:english "11th-grade Arabic"  :hebrew "ערבית - אוצר מילים י\"א"}
   :about             {:english "About"              :hebrew "אודות"}
   :and-not           {:english "and not"            :hebrew "ולא"}
   :arabic            {:english "arabic"             :hebrew "ערבית"}
   :basic-arabic      {:english "Basic Arabic"       :hebrew "ערבית בסיסי"}
   :both              {:english "Both"               :hebrew "שתיהם"}
   :buckets           {:english "buckets"            :hebrew "דליים"}
   :capital           {:english "capital"            :hebrew "בירה"}
   :correct-score     {:english "You are correct!"   :hebrew "נכון!"}
   :dictionary        {:english "Dictionary"         :hebrew "מילון"}
   :direction         {:english "direction"          :hebrew "כיוון"}
   :english           {:english "English"            :hebrew "אנגלית"}
   :flashcards        {:english "Flashcards"         :hebrew "כרטיסיות"}
   :free-text         {:english "Free text"          :hebrew "תשובות פתוחות"}
   :go-to-homepage    {:english "Go to Home Page"    :hebrew "חזרה לדף הבית"}
   :hebrew            {:english "Hebrew"             :hebrew "עברית"}
   :known-to-new      {:english "Known to new"       :hebrew "ידוע לחדש"}
   :multiple-choice   {:english "Multiple choice"    :hebrew "תשובות אמריקיות"}
   :new-to-known      {:english "New to known"       :hebrew "חדש לידוע"}
   :num-choices       {:english "Num choices"        :hebrew "מס' תשובות"}
   :options           {:english "Options"            :hebrew "אפשרויות"}
   :score             {:english "Score"              :hebrew "ניקוד"}
   :state             {:english "state"              :hebrew "מדינה"}
   :state-capitals    {:english "State capitals"     :hebrew "ערי בירה - מדינות ארה\"ב"}
   :prototype         {:english "Prototype"          :hebrew "אב טיפוס"}
   :show-choices      {:english "Show choices"       :hebrew "אפשרויות"}
   :start-game        {:english "Start game"         :hebrew "התחל משחק"}
   :ui-language       {:english "Interface language" :hebrew "שפת תצוגה"}

   ;; Interpolated strings (still a bit kludgy)
   :incorrect-score
   {:english
    (fn [answered-word correct-answer players-answer]
      (strint/<< "\"~{answered-word}\" is \"~{correct-answer}\", not \"~{players-answer}\"."))
    :hebrew
    (fn [answered-word correct-answer players-answer]
      (strint/<< "\"~{answered-word}\" היא \"~{correct-answer}\" ולא \"~{players-answer}\"."))
    }
   })
		



(defn lstr [language key]
  (cond
    (number? key) key

    :else
    (or (get-in string-table [key language]) key)))




