(ns flashcards.dicts.arabic-08
  (:require [flashcards.db :as DB]))

(def dict
  {:name :8th-grade-arabic-cms
   :source "http://cms.education.gov.il/EducationCMS/Units/Tochniyot_Limudim/LashonAravit/MaagareyMilim/lefiAlefBetAravi/Milim8.htm"
   :from-language :arabic
   :to-language :hebrew
   :answer-type :translation
   ::DB/num-buckets 5
   :words {"أَخَذَ - يَأْخُذُ"	" לקח"
           "أَخِير"	"אחרון"
           "أَرَادَ (ע'  يُرِيدُ )"	"רצה"
           "أرْض (ج) أَراضِي (أَراضِِ )"	"אדמה, ארץ"
           "أَرْكَان ٱلإسْلام"	"עמודי האסלאם, מצוות היסוד של האסלאם"
           "أَرِيحَا"	"יריחו"
           "أُسْبُوع (ج) أسَابِيع"	"שבוע"
           "ألإِسْلاَم"	"אסלאם"
           "أَلْجَلِيل"	"הגליל"
           "أَلْحَجّ"	"העלייה לרגל"
           "أَلْحَمْدُ  لِلّه"	"השבח לאל, תודה לאל"
           "أَلزَّكَاة"	"הצדקה"
           "أَلشَّهَادَة"	"באסלאם- אמירת העדוּת: 'أَشْهَدُ أَنْ لاَ إلهَ إِلاَّ
الله وَأَشْهَدُ أَنَّ مُحَمَّدََا رَسُول الله '"
           "أَلصَّلاَة"	"התפילה"
           "أَلصَّوْم"	"הצום"
           "أَلْعِرَاق"	"עיראק"
           "ألْقَادِم "	"הבא"
           "أََلْقَاهِرَة"	"קהיר"
           "ألْكَعْبَة"	"הכעבה  (במכה)"
           "ألْكُوَيْت"	"כווית"
           "ألله"	"אללה"
           "أَلْمَاضِي"	"שעבר (יום, שבוע וכו')"
           "ألْمَمْلَكة ٱلْعَرَبِيّة ٱلسُّعُودِيّة"	"ערב הסעודית"
           "أَلْيَابَان "	"יפן"
           "أَمَرَ -  يَأْمُرُ"	"ציווה"
           "أمْسِ  الأَوَّل"	"שלשום"
           "أَمِير"	"נסיך"
           "أمِيركا"	"אמריקה"
           "إِنْسَان (ج) ناس"	"בן-אדם, איש"
           "أُورُوبّا"	"אירופה"
           "أُولَى"	"ראשונה"
           "أَيّ"	"איזה"
           "إِيرَان"	"איראן"
           "أَيْضًا"	"גם כן"
           "بارِيس"	"פריס"
           "بَحْر(ج) بِحار, بُحُور"	"ים"
           "بَدَأَ - يَبْدَأُ"	"התחיל"
           "بَرِيطَانيا"	"בריטניה"
           "بُسْتَان (ج) بَسَاتِين"	"גן"
           "بَصَل"	"בצל"
           "بَعْدَ الظُّهْر"	"אחר הצהרים"
           "بَعِيد عَنْ"	"רחוק מ-"
           "بَغْداد"	"בגדאד"
           "بَلَدِيّة"	"עירייה"
           "بَنَى (ע'  يَبْنِي)"	"בנה"
           "تَحْتَ"	"תחת, מתחת ל-"
           "تَرَكَ - يَتْرُكُ"	"עזב, השאיר"
           "تَعْبَان"	"עייף"
           "تِلِفِزْيُون (ج)  ات"	"טלוויזיה"
           "تِلِفُون (ج) ات"	"טלפון"
           "تِلْكَ"	" ההיא"
           "ثُمَّ"	"אחר כך"
           "جَار (ج) جِيرَان"	"שָׁכֵן"
           "جَدّ (ج) أجْدَاد"	"סב"
           "جَدَّة (ج) ات"	"סבתא"
           "جِدًّا"	"מאד"
           "جُمْلة (ج) جُمَل"	"משפט"
           "جَنُوب"	"דרום"
           "جَوَاب (ج) أَجْوِبَة"	"תשובה"
           "حَاسُوب (ج) حَواسِيب"	"מחשב"
           "حَدِيث"	"חדיש, מודרני"
           "حَفْلَة (ج) حَفَلات"	"מסיבה, חגיגה"
           "حِكَايَة (ج) ات"	"סיפור"
           "حُكُومَة (ج) ات"	"ממשלה"
           "خَال (ج) أخْوَال"	"דוד (מצד האם)"
           "خَرَجَ - يَخْرُجُ"	"יצא"
           "خِلاَلَ "	"במשך, תוך (זמן)"
           "دَخَلَ - يَدْخُلُ"	" נכנס"
           "دَرْس  (ج) دُرُوس"	"שיעור"
           "دَوْلَة (ج) دُوَل"	"מדינה"
           "دِين (ج) أَدْيَان"	"דת"
           "ذٰلِكَ"	"ההוא"
           "رَئِيس (ج) رُؤَسَاء "	"נשיא, ראש"
           "رَئِيس الحُكُومَة"	"ראש הממשלה"
           "رِسَالَة (ج) رَسَائِل"	"איגרת, מכתב"
           "رَمَضَان"	"רמצ'אן"
           "زَرَعَ - يَزْرَعُ"	"זרע"
           "زَمَان"	"זמן"
           "سُؤَال (ج) أَسْئِلَة"	"שאלה"
           "سَافَرَ"	"נסע"
           "سَمِعَ - يَسْمَعُ"	"שמע"
           "سُوق (זו'נ) (ج) أَسْوَاق"	"שוק"
           "شَارِع (ج) شَوَارِع  "	"רחוב"
           "شُبَّاك (ج) شَبَابِيك"	"חלון"
           "شَرْق"	"מזרח"
           "شُكْرََا  عَلَى  -  عَفْوًا"	"תודה על- בבקשה"
           "شَمَال"	"צפון, שמאל"
           "صَيْف"	"קיץ"
           "ضَحِكَ - يَضْحَكُ"	"צחק"
           "طَاوِلَة (ج) ات"	"שולחן"
           "طَبِيب (ج) أَطِبّاء"	"רופא"
           "طَرِيق  (ج) طُرُق"	"דרך, כביש"
           "طَلَبَ - يَطْلُبُ"	"ביקש"
           "طَوِيل"	"ארוך, גבוה"
           "طَيِّب"	"טוב"
           "عائِلَة (ج) ات"	"משפחה"
           "عَرَفَ - يَعْرِفُ "	"ידע, הכיר"
           "عَسَل"	"דבש"
           "عُطْْلَة (ج) ات"	"חופשה"
           "عَكَّا"	"עכו"
           "عَمّ (ج) أَعْمَام"	"דוד (מצד האב)"
           "عَمَل (ج) أَعْمَال"	"עבודה"
           "عَنْ"	"על, על אודות"
           "عِنْدَمَا"	"כאשר"
           "عُنْوَان (ج) عَنَاوِين"	"כתובת, כותרת"
           "عِيد (ج) أعْيَاد"	"חג"
           "عِيد ٱلأَضْحَى"	"חג הקרבן (באסלאם)"
           "عِيد ٱلفِطْر"	"חג הפסקת הצום (באסלאם)"
           "عِيد مِيلاد"	"יום הולדת"
           "غَدًا"	"מחר"
           "فَرَنْسَا"	"צרפת"
           "فَلاّح (ج) فَلاّحُونَ"	"איכר"
           "فُنْدُق (ج) فَنادِق"	"בית מלון, פונדק"
           "فَهِمَ - يَفْهَمُ"	"הבין"
           "فِيلم (ج) أَفْلام"	"סרט"
           "قَامَ (ע' يَقُومُ)"	"קם"
           "قَامَ (ע' يَقُومُ) بِ"	"עסק ב-, ערך (ביקור)"
           "قَدْ"	"כבר (מילית להדגשת העבר)"
           "قَدِمَ - يَقْدَمُ"	"בא"
           "قَدِيم"	"ישן, עתיק, קדום"
           "قَرْيَة (ج) قُرَى (قُرًى)"	"כפר"
           "قِصَّة (ج) قِصَص"	"סיפור"
           "قَصِير"	"קצר, נמוך"
           "قَلَم (ج) أَقْلام"	"עט"
           "كُرْسِيّ (ج) كَرَاسِي (كَرَاسٍ)"	"כיסא"
           "كَلام (ז')"	"דיבור, דברים"
           "لَنْدَن"	"לונדון"
           "مَبْرُوك عَلَى - أَلله يبارِك فِيك / بِك"	"מזל טוב, תתחדש"
           "مَتَى"	"מתי?"
           "مَرْكَز (ج) مَرَاكِز"	"מרכז"
           "مَرْكَزِيّ"	"מרכזי"
           "مَرِيض (ج) مَرْضَى"	"חולה"
           "مَسَاء"	"ערב"
           "مَسَاء الخَيْر - مَسَاء النُّور"	"ערב טוב"
           "مَسْجِد (ج) مَسَاجِد"	"מסגד"
           "مُسْلِم (ج) مُسْلِمُونَ"	"מוסלמי"
           "مَسِيحِيّ (ج) مَسِيحِيُّونَ"	"נוצרי"
           "مِصْر"	"מצרים"
           "مَكَان (ج) أَمَاكِن"	"מקום"
           "مَكْتَبَة (ج) ات"	"ספרייה, מכתבה"
           "مُمْتَاز"	"מצוין"
           "نَبِيّ (ج) أَنْبِيَاء"	"נביא"
           "هٰؤُلاءِ"	"אלה, האלה"
           "وَالِد"	"אב, הורה"
           "وَالِدَة"	"אם"
           "وَراءَ"	"מאחורי (מקום)"
           "وَظِيفَة"	"מטלה (שיעורי-בית)"
           "وَقَفَ - (ע' يَقِفُ)"	"עמד"
           "يَمِين"	"ימין"
           "يَوْم ٱلأَحَد"	"יום ראשון"
           "يَوْم ٱلجُمْعَة"	"יום שישי"
           "يَوْم ٱلْخَمِيس"	"יום חמישי"
           "يَوْم ٱلسَّبْت"	"יום שבת"
           }})
