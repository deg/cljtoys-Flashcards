(ns flashcards.dicts.arabic-07
  (:require [flashcards.db :as DB]))

(def dict
  {:name :7th-grade-arabic-cms
   :source "http://cms.education.gov.il/EducationCMS/Units/Tochniyot_Limudim/LashonAravit/MaagareyMilim/lefiAlefBetAravi/Milim7.htm"
   :from-language :arabic
   :to-language :hebrew
   :answer-type :translation
   ::DB/num-buckets 5
   :words {"أَ"	"האם?"
           "أَب"	"אב"
           "أَبْيَض"	"לבן"
           "إِبْن (ج) أَبْنَاء"	"בן"
           "أَخ (ج) إخْوَة"	"אח"
           "أُخْت (ج) أَخَوَات"	"אחות"
           "أدِيب"	"אדיב, מנומס"
           "أُذُن"	"אוזן"
           "أُسْتَاذ (ج) أَسَاتِذَة"	"מורה"
           "إِسْرَائِيل"	"ישראל"
           "إِسْم (ج) أَسْمَاء"	"שֵם"
           "أَكَلَ - يَأْكُلُ"	"אכל"
           "أَلأُرْدُنّ"	"ירדן"
           "ألْقُرْآن"	"הקוראן"
           "إِلَى"	"ל-, אל"
           "إلَى أَيْنَ"	"לאן?"
           "أُمّ"	"אֵם"
           "أَمَامَ"	"מול, לפני (מקום)"
           "أَمْسِ"	"אתמול"
           "أَنَا"	"אני"
           "أَنْتَ"	"אתה"
           "أَنْتِ"	"את"
           "أَنْتُمْ"	"אתם"
           "أَنْتُنَّ"	"אתן"
           "أَهْل"	"משפחה, אנשי-, בני-"
           "أَهْلا"	"שלום, ברוך הבא"
           "أَهْلاً وَسَهْلاً - أَهْلاً فِيك / بِك"	"ברוך הבא"
           "أَوْ"	"או"
           "أُورشَلِيم الْقُدْس"	"ירושלים"
           "أَوَّل"	"ראשון"
           "إيلات"	"אילת"
           "أَيْنَ"	"היכן? איפה?"
           "بِ"	"בְּ-, באמצעות"
           "بَاب (ج) أَبْوَاب"	"דלת, שער"
           "بَاص (ج) ات"	"אוטובוס"
           "بَدَوِيّ (ج) بَدْو"	"בדווי, בדואי"
           "بَرِيد"	"דואר"
           "بَعْدَ"	"אחרי (זמן)"
           "بِلاَد (נ\") (ج) بُلْدَان"	"ארץ"
           "بَلَد (ז\") (ج) بِلاد"	"עיר, ארץ"
           "بِنْت (ج) بَنَات"	"בת, ילדה"
           "بَنْك"	"בנק"
           "بَيْت (ج) بُيُوت"	"בית"
           "بَيْرُوت"	"ביירות"
           "بَيْنَ"	"בין"
           "تَلّ أَبِيب"	"תל-אביב"
           "تِلْمِيذ (ج) تَلامِيذ"	"תלמיד"
           "تِلْمِيذَة (ج) تِلْمِيذَات"	"תלמידה"
           "تَمْرِين"	"תרגיל"
           "تُوت"	"תותים"
           "ثَوْب (ج) ثِيَاب"	"בֶּגד"
           "جَبَل (ج) جِبال"	"הר"
           "جَدِيد (ج) جُدُد"	"חדש"
           "جَلَسَ - يَجْلِسُ"	"ישב"
           "جَمِيل"	"יפה"
           "حَال"	"מצב"
           "حَلِيب"	"חלב"
           "حَيْفَا"	"חיפה"
           "دَار(נ\") (ج) دُور"	"בית, דירה"
           "دُرْزِيّ (ج) دُرُوز"	"דרוזי"
           "دَرَسَ - يَدْرُسُ"	"למד"
           "دَفْتَر (ج) دَفَاتِر"	"מחברת"
           "دُكّان (ז\") (ج) دَكَاكِين"	"חנות"
           "دُكْتُور"	"דוקטור, רופא"
           "دِمَشْق"	"דמשק"
           "دَوْر"	"תור, תפקיד"
           "ذَهَبَ - يَذْهَبُ"	"הלך"
           "رَأْس"	"ראש"
           "رَأََى"	"ראה"
           "رَجَعَ - يَرْجِعُ"	"חזר"
           "رَجُل (ج) رِجَال"	"גבר"
           "رَفِيق (ج) رِفَاق"	"חבר"
           "رَكِبَ - يَرْكَبُ א"	"נסע ב-"
           "زَارَ א (ע\" يَزُورُ )"	"ביקר ב-"
           "زِيَارَة"	"ביקור"
           "زَيْت"	"שמן"
           "زَيْتُون (ז\")"	"זיתים"
           "سَاعَة (ج) ات"	"שעה, שעון"
           "سَأَلَ - يَسْأَلُ"	"שאל"
           "سَكَنَ - يَسْكُنُ"	"גר, שכן"
           "سَلام"	"שלום"
           "سَنَة (ج) سَنَوات"	"שנה"
           "سُوريَا"	"סוריה"
           "شَرِبَ - يَشْرَبُ"	"שתה"
           "شَمْس"	"שמש"
           "شَهْر (ج) شُهُور"	"חודש"
           "صَبَاح"	"בוקר"
           "صَبَاح الخَيْر - صَبَاح النُّور"	"בוקר טוב- בוקר אור"
           "صَغِير (ج) صِغَار"	"קטן"
           "صَفّ (ج) صُفُوف"	"כיתה"
           "ضَيْف (ج) ضُيُوف"	"אורח"
           "ظُهْر"	"צהרים"
           "عَاصِمَة (ج) عَواصِم"	"עיר בירה"
           "عَرَبِيّ (ج) عَرَب"	"ערבי"
           "عَلَى"	"על"
           "عَمّان"	"רבת-עמון"
           "عَمِلَ - يَعْمَلُ"	"עבד, עשה"
           "عِنْدَ"	"אצל"
           "عَيْن (ج) عُيُون"	"עין, מעיין"
           "غُرْفَة (ج) غُرَف"	"חדר"
           "فَ"	"ו-, ואז"
           "فَتَحَ - يَفْتَحُ"	"פתח"
           "فِي"	"ב-, בתוך"
           "قَالَ (ע\" يَقُولُ )"	"אמר"
           "قَبْلَ"	"לפני (זמן)"
           "قَرَأَ - يَقْرَأُ"	"קרא"
           "قَرِيب مِنْ"	"קרוב ל-"
           "قَلِيل"	"מעט, קצת"
           "قَهْوَة"	"קפה"
           "كَاتِب (ج) كُتَّاب"	"סופר, פקיד"
           "كَانَ"	"(ע\" يَكُونُ)- היה"
           "كَبِير (ج) كِبَار"	"גדול"
           "كِتَاب (ج) كُتُب"	"ספר"
           "كَتَبَ - يَكْتُبُ"	"כתב"
           "كَثِير (ج) كَثِيرُونَ"	"רב, הרבה"
           "كُلّ"	"כל"
           "كَلْب (ج) كِلاب"	"כלב"
           "كَيْفَ"	"איך"
           "كَيْفَ حَالُكَ ؟ - مَبْسُوط/ة"	"מה שלומך? - בסדר גמור, שבע רצון
(מבסוט/ה)"
           "لِ"	"ל-"
           "لاَ"	"לא (גם מילת שלילה לעתיד)"
           "لَبِسَ - يَلْبَسُ"	"לבש"
           "لُبْنَان"	"לבנון"
           "لُغَة (ج) ات"	"שפה"
           "لِمَاذَا"	"למה? מדוע?"
           "لَوْح"	"לוח"
           "لَيْل/ لَيْلَة"	"לילה"
           "ما ?1?"	"לא (שלילת העבר)"
           "ما ?2?"	"מה? (מילת שאלה לפני שם עצם)"
           "مَاذَا"	"מה? (מילת שאלה לפני פועל)"
           "مَدْرَسَة (ج) مَدارِس"	"בית-ספר"
           "مُدِير (ج) مُدِيرُونَ, مُدَرَاء"	"מנהל"
           "مَدِينَة (ج) مُدُن"	"עיר"
           "مَرْحَبًا - مَرْحَبْتين"	"שלום, ברוך הבא"
           "مَطْبَخ"	"מטבח"
           "مَطَر"	"גשם"
           "مَعَ"	"עִם"
           "مُعَلِّم (ج) مُعَلِّمُونَ"	"מורה"
           "مَكَّة"	"מֶכּה"
           "مَكْتَب (ج) مَكَاتِب"	"משרד"
           "مَكْتوب (ج) مَكَاتِيب"	"מכתב"
           "مَلِك (ج) مُلُوك"	"מלך"
           "مِنْ"	"מ-, מן"
           "مِنْ أَيْنَ"	"מהיכן?"
           "مَنْ"	"מי?"
           "نَتَانيا"	"נתניה"
           "نَحْنُ"	"אנחנו"
           "نَزَلَ - يَنْزِلُ"	"ירד"
           "نَعَمْ"	"כן"
           "نَهْر"	"נהר"
           "هٰذَا"	"זה, הזה"
           "هٰذِهِ"	"זאת, הזאת"
           "هَلْ"	"האם?"
           "هُمْ"	"הם"
           "هُنَّ"	"הן"
           "هُنَا"	"כאן"
           "هُنَاكَ"	"שם"
           "هُوَ"	"הוא"
           "هِيَ"	"היא"
           "وَ"	"ו-"
           "وَرْد"	"פרחים, ורדים"
           "وَزِير (ج) وُزَرَاء"	"שַׂר"
           "وَصَلَ"	"הגיע, בא"
           "وَلَد (ج) أَوْلاد"	"יֶלֶד"
           "يَا"	"הוי (מילת קריאה)"
           "يَافَا"	"יפו"
           "يَد (נ\")"	"יד"
           "يَهُودِيّ (ج) يَهُود"	"יהודי"
           "يَوْم (ج) أَيَّام"	"יום"

           }})