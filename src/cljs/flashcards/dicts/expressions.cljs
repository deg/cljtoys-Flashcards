(ns flashcards.dicts.expressions
  (:require [flashcards.db :as DB]))

(def dict
  {:name :expressions
   :source "unknown"
   :from-language :arabic
   :to-language :hebrew
   :answer-type :translation
   ::DB/num-buckets 5
   :words {
           "كان"
           "היה"

           "ليس"
           "אין, איננו (ك)"

           "صار"
           "נעשה (ك)"

           "اصبح"
           "הפך ל.. (ك)"

           "ما دام"
           "כל עוד.. (ك)"

           "ما زال"
           "עודנו (ك)"

           "لا يزال"
           "עודנו (ك)"

           "لم يزل"
           "עודנו (ك)"

           "الّذي"
           "יחיד (אשר)"

           "الّتي"
           "יחידה (אשר)"

           "الذين"
           "רבים (אשר)"

           "اللواتي"
           "רבות (אשר)"

           "اللّذان"
           "זוגי זכר (אשר)"

           "اللّتان"
           "זוגי נקבה"

           "تحمّل المسؤوليّة"
           "נשיאה באחריות"

           "حبّ الوطن"
           "אהבת המולדת"

           "التّعايش"
           "דו קיום"

           "قيمة"
           "ערך"

           "لعلّ"
           "אולי, אפשר ש (أ)"

           "ليت"
           "הלוואי ש.. (أ)"

           "لأنّ"
           "מפני ש.. (أ)"

           "كأنّ"
           "כאילו ש.. (أ)"

           "لكنّ" 
           "אבל (أ)"

           }})