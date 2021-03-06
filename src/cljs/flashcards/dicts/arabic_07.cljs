(ns flashcards.dicts.arabic-07
  (:require [flashcards.db :as DB]))

(def dict
  {:name :7th-grade-arabic-cms
   :source "http://cms.education.gov.il/EducationCMS/Units/Tochniyot_Limudim/LashonAravit/MaagareyMilim/lefiAlefBetAravi/Milim7.htm"
   :from-language :arabic
   :to-language :hebrew
   :answer-type :translation
   ::DB/num-buckets 5
   :words {
           "أَ"
           "האם?\u200F"

           "أَب"
           "אב"

           "أَبْيَض"
           "לבן"

           "إِبْن"
           "בן" 

           "أَبْنَاء"
           "בנים"

           "أَخ" 
           "אח"

           "إخْوَة"
           "אחים"

           "أُخْت" 
           "אחות"

           "أَخَوَات"
           "אחיות"

           "أدِيب"
           "אדיב, מנומס"

           "أُذُن"
           "אוזן"

           "أُسْتَاذ" 
           "מורה"

           "أَسَاتِذَة"
           "מורים"

           "إِسْرَائِيل"
           "ישראל"

           "إِسْم" 
           "שֵם"

           "أَسْمَاء"
           "שמות"

           "أَكَلَ" 
           "אכל"

           "يَأْكُلُ"
           "יאכל"

           "أَلأُرْدُنّ"
           "ירדן"

           "ألْقُرْآن"
           "הקוראן"

           "إِلَى"
           "ל-, אל"

           "إلَى أَيْنَ"
           "לאן?\u200F"

           "أُمّ"
           "אֵם"

           "أَمَامَ"
           "מול, לפני (מקום)\u200F"

           "أَمْسِ"
           "אתמול"

           "أَنَا"
           "אני"

           "أَنْتَ"
           "אתה"

           "أَنْتِ"
           "את"

           "أَنْتُمْ"
           "אתם"

           "أَنْتُنَّ"
           "אתן"

           "أَهْل"
           "משפחה, אנשי-, בני-\u200F"

           "أَهْلا"
           "שלום, ברוך הבא"

          "أَهْلاً وَسَهْلاً" 
           "ברוך הבא"

           "أَهْلاً فِيك"
           "ברוך הבא"

           "أَهْلاً بِك"
           "ברוכה הבאה"

           "أَوْ"
           "או"

           "أُورشَلِيم الْقُدْس"
           "ירושלים"

           "أَوَّل"
           "ראשון"

           "إيلات"
           "אילת"

           "أَيْنَ"
           "היכן? איפה?\u200F"

           "بِ"
           "בְּ-, באמצעות"

           "بَاب" 
           "דלת, שער"

           "أَبْوَاب"
           "דלתות, שערים"

           "بَاص "
           "אוטובוס"

           " بَاصات"
           "אוטובוסים"

           "بَدَوِيّ" 
           "בדווי, בדואי"

           "بَدْو"
           "בדווים, בדואים"

           "بَرِيد"
           "דואר"

           "بَعْدَ"
           "אחרי (זמן)\u200F"

           "بِلاَد"
           "ארץ"

           "بُلْدَان"
           "ארצות"

           "بَلَد"
           "עיר, ארץ"

           " بِلاد"
           "ערים, ארצות"

           "بِنْت"
           "בת, ילדה"

           "بَنَات"
           "בנות, ילדות"

           "بَنْك"
           "בנק"

           "بَيْت"
           "בית"

           "بُيُوت"
           "בתים"


           "بَيْرُوت"
           "ביירות"

           "بَيْنَ"
           "בין"

           "تَلّ أَبِيب"
           "תל-אביב"

           "تِلْمِيذ"
           "תלמיד"

           "تَلامِيذ"
           "תלמידים"

           "تِلْمِيذَة"
           "תלמידה"

           "تِلْمِيذَات"
           "תלמידות"

           "تَمْرِين"
           "תרגיל"

           "تُوت"
           "תותים"

           "ثَوْب"
           "בֶּגד"

           "ثِيَاب"
           "בגדים"

           "جَبَل"
           "הר"

           "جِبال"
           "הרים"

           "جَدِيد"
           "חדש"

           "جُدُد"
           "חדשים"

           "جَلَسَ"
           "יֶֶָשב"

           "يَجْلِسُ"             
           "יֶשב"

           "جَمِيل"
           "יפה"

           "حَال"
           "מצב"

           "حَلِيب"
           "חלב"

           "حَيْفَا"
           "חיפה"

           "دَار"
           "בית, דירה"

           "دُور"
           "בתים, דירות"

           "دُرْزِيّ"
           "דרוזי"

           "دُرُوز"
           "דרוזים"

           "دَرَسَ"
           "למד"

           "يَدْرُسُ"
           "ילמד"

           "دَفْتَر"
           "מחברת"

           "دَفَاتِر"
           "מחברות"

           "دُكّان"
           "חנות"

           "دَكَاكِين"
           "חנויות"

           "دُكْتُور"
           "דוקטור, רופא"

           "دِمَشْق"
           "דמשק"

           "دَوْر"
           "תור, תפקיד"

           "ذَهَبَ"
           "הלך"

           "يَذْهَبُ"
           "ילך"

           "رَأًس"
           "ראש"

           "رَأَى"
           "ראה"

           "رَجَعَ"
           "חזר"

           "يَرْجِعُ"
           "יחזור"

           "رَجُل"
           "גבר"

           "رِجَال"
           "גברים"

           "رَفِيق"
           "חבר"

           "رِفَاق"
           "חברים"
           "رَكِبَ"
           "נסע ב-\u200F"

           "يَرْكَبُ"
           "יסע ב-\u200F"

           "زَارَ"
           "ביקר ב-\u200F"

           "يَزُورُ"
           "יבקר ב-\u200F"

           "زِيَارَة"
           "ביקור"

           "زَيْت"
           "שמן"

           "زَيْتُون"
           "זיתים"

           "سَاعَة"
           "שעה, שעון"

           "سَاعَات"
           "שעות, שעונים"

           "سَأَلَ"
           "שאל"

           "يَسْأَلُ"
           "ישאל"

           "سَكَنَ"
           "גר, שכן"

           "يَسْكُنُ"
           "גרים, שכנים"

           "سَلام"
           "שלום"

           "سَنَة"
           "שנה"

           "سَنَوات"
           "שנים"

           "سُوريَا"
           "סוריה"

           "شَرِبَ"
           "שתה"

           "يَشْرَبُ"
           "ישתה"

           "شَمْس"
           "שמש"

           "شَهْر"
           "חודש"

           "شُهُور"
           "חודשים"

           "صَبَاح"
           "בוקר"

           "صَبَاح الخَيْر"
           "בוקר טוב"

           "صَبَاح النُّور"
           "בוקר אור"

           "صَغِير"
           "קטן"

           "صِغَار"
           "קטנים"

           "صَفّ"
           "כיתה"

           "صُفُوف"
           "כיתות"

           "ضَيْف"
           "אורח"

           "ضُيُوف"
           "אורחים"

           "ظُهْر"
           "צהרים"

           "عَاصِمَة"
           "עיר בירה"

           "عَواصِم"
           "עירי בירה"

           "عَرَبِيّ"
           "ערבי"

           "عَرَب"
           "ערבים"

           "عَلَى"
           "על"

           "عَمّان"
           "רבת-עמון"

           "عَمِلَ"
           "עבד, עשה"

           "يَعْمَلُ"
           "יעבוד, יעשה"

           "عِنْدَ"
           "אצל"

           "عَيْن"
           "עין, מעיין"

           "عُيُون"
           "עניים, מעיינים"

           "غُرْفَة"
           "חדר"

           "غُرَف"
           "חדרים"

           "فَ"
           "ו-, ואז"

           "فَتَحَ"
           "פתח"

           "يَفْتَحُ"
           "יפתח"

           "فِي"
           "ב-, בתוך"

           "قَالَ"
           "אמר"

           "يَقُولُ"
           "יאמר"

           "قَبْلَ"
           "לפני (זמן)\u200F"

           "قَرَأَ"
           "קרא"

           "يَقْرَأُ"
           "יקרא"

           "قَرِيب مِنْ"
           "קרוב ל-\u200F"

           "قَلِيل"
           "מעט, קצת"

           "قَهْوَة"
           "קפה"

           "كَاتِب"
           "סופר, פקיד"

           "كُتَّاب"
           "סופרים, פקידים"

           "كَانَ"
           "היה"

           "يَكُونُ"
           "יהיה"

           "كَبِير"
           "גדול"

           "كِبَار"
           "גדולים"

           "كِتَاب"
           "ספר"

           "كُتُب"
           "ספרים"

           "كَتَبَ"
           "כתב"

           "يَكْتُبُ"
           "יכתוב"

           "كَثِير"
           "רב, הרבה"

           "كَثِيرُونَ"
           "רבים"

           "كُلّ"
           "כל"

           "كَلْب"
           "כלב"

           "كِلاب"
           "כלבים"

           "كَيْفَ"
           "איך"

           "كَيْفَ حَالُكَ؟"
           "מה שלומך?\u200F"

           "مَبْسُوط"
           "בסדר גמור, שבע רצון"

           "مَبْسُوطة"
           "בסדר גמור, שבעת רצון"

           "لِ"
           "ל-\u200F"

           "لاَ"
           "לא (גם מילת שלילה לעתיד)\u200F"

           "لَبِسَ - يَلْبَسُ"
           "לבש"

           "لُبْنَان"
           "לבנון"

           "لُغَة"
           "שפה"

           "لُغَات"
           "שפות"

           "لِمَاذَا"
           "למה? מדוע?\u200F"

           "لَوْح"
           "לוח"

           "لَيْل/ لَيْلَة"
           "לילה"

           "ما ?1?\u200F"
           "לא (שלילת העבר)\u200F"

           "ما ?2?\u200F"
           "מה? (מילת שאלה לפני שם עצם)\u200F"

           "مَاذَا"
           "מה? (מילת שאלה לפני פועל)\u200F"

           "مَدْرَسَة"
           "בית-ספר"

           "مَدارِس"
           "בתי ספר"

           "مُدِير"
           "מנהל"

           "مُدِيرُونَ"
           "מנהלים"

           "مُدَرَاء"
           "מנהלים"

           "مَدِينَة"
           "עיר"

           "مُدُن"
           "ערים"

           "مَرْحَبًا"
           "שלום, ברוך הבא"

           "مَرْحَبْتين"
           "שלום, ברוכים הבאים"

           "مَطْبَخ"
           "מטבח"

           "مَطَر"
           "גשם"

           "مَعَ"
           "עִם"

           "مُعَلِّم"
           "מורה"

           "مُعَلِّمُونَ"
           "מורים"

           "مَكَّة"
           "מֶכּה"

           "مَكْتَب"
           "משרד"

           "مَكَاتِب"
           "משרדים"

           "مَكْتوب"
           "מכתב"

           "مَكَاتِيب"
           "מכתבים"

           "مَلِك"
           "מלך"

           "مُلُوك"
           "מלכים"

           "مِنْ"
           "מ-, מן"

           "مِنْ أَيْنَ"
           "מהיכן?\u200F"

           "مَنْ"
           "מי?\u200F"

           "نَتَانيا"
           "נתניה"

           "نَحْنُ"
           "אנחנו"

           "نَزَلَ"
           "יָרד"

           "يَنْزِلُ"
           "יֶרד"

           "نَعَمْ"
           "כן"

           "نَهْر"
           "נהר"

           "هٰذَا"
           "זה, הזה"

           "هٰذِهِ"
           "זאת, הזאת"

           "هَلْ"
           "האם?\u200F"

           "هُمْ"
           "הם"

           "هُنَّ"
           "הן"

           "هُنَا"
           "כאן"

           "هُنَاكَ"
           "שם"

           "هُوَ"
           "הוא"

           "هِيَ"
           "היא"

           "وَ"
           "ו-\u200F"

           "وَرْد"
           "פרחים, ורדים"

           "وَزِير"
           "שַׂר"

           "وُزَرَاء"
           "שרים"

           "وَصَلَ"
           "הגיע, בא"

           "وَلَد"
           "יֶלֶד"

           "أَوْلاد"
           "ילדים"

           "يَا"
           "הוי (מילת קריאה)\u200F"

           "يَافَا"
           "יפו"

           "يَد"
           "יד"

           "يَهُودِيّ"
           "יהודי"

           "يَهُود"
           "יהודים"

           "يَوْم"
           "יום"

           "أَيَّام"
           "ימים"
           }})
