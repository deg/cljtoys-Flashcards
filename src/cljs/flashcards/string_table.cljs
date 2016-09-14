(ns flashcards.string-table
  "Quik-n-dirty i10n translation tables"
  (:require-macros
   [clojure.core.strint :as strint]))

(def ^:private string-table
  {:11th-grade-arabic {:english "Aviva's 11th-grade Arabic"  :hebrew "ערבית של אביבה - י\"א"}
   :7th-grade-arabic-cms  {:english "7th-grade Arabic"  :hebrew "ערבית - אוצר מילים ז"}
   :8th-grade-arabic-cms  {:english "8th-grade Arabic"  :hebrew "ערבית - אוצר מילים ח"}
   :9th-grade-arabic-cms  {:english "9th-grade Arabic"  :hebrew "ערבית - אוצר מילים ט"}
   :10th-grade-arabic-cms {:english "10th-grade Arabic" :hebrew "ערבית - אוצר מילים י"}
   :11th-grade-arabic-cms {:english "11th-grade Arabic" :hebrew "ערבית - אוצר מילים י\"א"}
   :12th-grade-arabic-cms {:english "12th-grade Arabic" :hebrew "ערבית - אוצר מילים י\"ב"}
   :about             {:english "About"              :hebrew "אודות"}
   :and-not           {:english "and not"            :hebrew "ולא"}
   :arabic            {:english "arabic"             :hebrew "ערבית"}
   :basic-arabic      {:english "Basic Arabic"       :hebrew "ערבית בסיסי"}
   :both              {:english "Both"               :hebrew "שתיהם"}
   :buckets           {:english "decks"              :hebrew "חפיסות"}
   :buckets-empty     {:english "The selected decks are empty. Great job!"
                       :hebrew "החפיסות שנבחרו ריקות. עבודה טובה!"}
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
   :reset-game        {:english "Reset game"         :hebrew "אפס חפיסות"}
   :show-choices      {:english "Show choices"       :hebrew "אפשרויות"}
   :start-game        {:english "Start game"         :hebrew "התחל משחק"}
   :translation       {:english "translation"        :hebrew "תרגום"}
   :ui-language       {:english "Interface language" :hebrew "שפת תצוגה"}

   ;; Interpolated strings (still a bit kludgy)
   :incorrect-score-forward
   {:english
    (fn [answer-type word correct-answer players-answer word-for-players-answer]
      (strint/<< "The ~{answer-type} of ~{word} is ~{correct-answer}, not ~{players-answer}."))
    :hebrew
    (fn [answer-type word correct-answer players-answer word-for-players-answer]
      (strint/<< "ה~{answer-type} של ~{word} היא ~{correct-answer} ולא ~{players-answer}."))
    }

   :incorrect-score-forward-second-line
   {:english
    (fn [answer-type word correct-answer players-answer word-for-players-answer]
      (strint/<< "~{players-answer} is the ~{answer-type} of ~{word-for-players-answer}."))
    :hebrew
    (fn [answer-type word correct-answer players-answer word-for-players-answer]
      (strint/<< "~{players-answer} היא ה~{answer-type} של ~{word-for-players-answer}"))
    }

   :incorrect-score-reverse
   {:english
    (fn [answer-type word correct-answer players-answer word-for-players-answer]
      (strint/<< "~{word} is the ~{answer-type} of ~{correct-answer}, not of ~{players-answer}."))
    :hebrew
    (fn [answer-type word correct-answer players-answer word-for-players-answer]
      (strint/<< "~{word} היא ה~{answer-type} של ~{correct-answer}, לא של ~{players-answer}."))
    }

   :incorrect-score-reverse-second-line
   {:english
    (fn [answer-type word correct-answer players-answer word-for-players-answer]
      (strint/<< "~{word-for-players-answer} is the ~{answer-type} of ~{players-answer}."))
    :hebrew
    (fn [answer-type word correct-answer players-answer word-for-players-answer]
      (strint/<< "~{word-for-players-answer} היא ה~{answer-type} של ~{players-answer}."))}

   })
		



(defn lstr [language key]
  (cond
    (number? key) key

    :else
    (let [key-sans-namespace (-> key name keyword)]
      (or (get-in string-table [key-sans-namespace language]) key))))




