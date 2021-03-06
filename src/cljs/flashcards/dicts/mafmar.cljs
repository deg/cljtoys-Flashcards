(ns flashcards.dicts.mafmar
  (:require [flashcards.db :as DB]))

(def dict
  {:name :mafmar
   :source "unknown"
   :from-language :arabic
   :to-language :hebrew
   :answer-type :translation
   ::DB/num-buckets 5
   :words {
           "فَصْل"
           "הפרדה"

           "بِالفِعْل"
           "למעשה"

           "فَ رْع"
           "סניף"

           "فروع"
           "סניפים"

           "مَصْرِفِيّة"
           "בנקאית"

           "حظر... على"
           "אסר דבר על"

           "وظّف"
           "העסיק"

           "فُرصة"
           "הזדמנות"

           "فُ رَص"
           "הזדמניות"

           "مِهنِيّ "
           "מקצועי"

           "أصاب "
           "פגע ב"

           "صوّت"
           "בחר (בבחירות)"

           "ألشّريعة الإسلًاميّة"
           "ההלכה האיסלמית"

           "لَّ مثيلَ له "
           "אין דומה לו"

           "حذّر"
           "הזהיר"

           "أدّى إلى"
           "גרם ל.."

           "جِهاز"
           "מכשיר"

           "أجهِزة" 
           "מכשירים"

           "تشغيل"
           "הפעלה"

           "إحْتاج إلى"
           "נזקק ל"

           "دِراسة"
           "מחקר"

           "تَدَهْوَرَ"
           "הידרדרות"

           "ضَرَر"
           "נזק"

           "أضرار"
           "נזקים"

           "حاسّة"
           "חוש"

           "حَواسّ"
           "חושים"

           "قَ لَق"
           "דאגה"

           "تعرّض لِ"
           "נחשף ל"
           }})
