(ns flashcards.dicts.arabic-09
  (:require [flashcards.db :as DB]))

(def dict
  {:name :9th-grade-arabic-cms
   :source "http://cms.education.gov.il/EducationCMS/Units/Tochniyot_Limudim/LashonAravit/MaagareyMilim/lefiAlefBetAravi/Milim9.htm"
   :from-language :arabic
   :to-language :hebrew
   :answer-type :translation
   ::DB/num-buckets 5
   :words {"إِتِّفَاق (ج) ات"	"הסכם"
           "إجْتِمَاع"	"פגישה, כינוס"
           "إجْتَمَعَ بِ , مَعَ"	"נפגש עם"
           "أَحَبَّ (ע\" يُحِبُّ)"	"אהב, רצה"
           "أَحْمَر"	"אדום"
           "أَخْضَر"	"ירוק"
           "أرْبَع"	"ארבע"
           "أرْبَعَة"	"ארבעה"
           "أَزْرَق"	"כחול"
           "إسْتَغْرَقَ"	"נמשך"
           "إِسْتِرَاحَة"	"הפסקה"
           "إسْتَقْبَلَ"	"קיבל את פני"
           "إسْتَمَعَ إلَى"	"הקשיב ל..."
           "أَسْوَد"	"שחור"
           "إشْتَرَكَ"	"השתתף"
           "أصْغَر"	"יותר קטן"
           "أَصْفَر"	"צהוב"
           "إِفْرِيقيا"	"אפריקה"
           "أَكْبَر"	"יותר גדול"
           "ألأُمَم ٱلمتَّحِدَة"	"האו\"ם"
           "أَلآن"	"עכשיו"
           "ألَّتِي"	"אשר (היא)"
           "أَلْخَلِيل"	"חברון"
           "ألَّذِي"	"אשר (הוא)"
           "ألَّذِينَ"	"אשר (הם)"
           "أَلشَّرْق ٱلأَوْسَط"	"המזרח התיכון"
           "ألضّفَّة ٱلغَرْبِيَّة"	"הגדה המערבית"
           "أللّوَاتِي"	"אשר (הן)"
           "أَلْمَانيا"	"גרמניה"
           "ألنَّاصِرَة"	"נצרת"
           "أَلْوِلاَيات ٱلْمُتَّحِدَة"	"ארה\"ב"
           "أَمَّا...فَ"	"באשר ל- הרי-"
           "إِمْتِحَان (ج) ات"	"מבחן"
           "إِمْرَأَة (ج) نِسَاء"	"אישה"
           "إنْتَخَبَ"	"בחר"
           "إنْتَظَرَ"	"חיכה"
           "بِئْر ٱلسَّبْع"	"באר-שבע"
           "بِٱلْقُرْب مِنْ"	"בקרבת-"
           "بِدَايَة"	"התחלה"
           "بَرْنَامَج (ج) بَرَامِج"	"תכנית"
           "بَقِيَ (ע\" يَبْقَى)"	"נשאר"
           "بَكَى - ( ע\" يَبْكِي)"	"בכה"
           "تَاجِر (ج) تُجّار"	"סוחר"
           "تَأْرِيخ"	"היסטוריה, תאריך"
           "تِسْع"	"תשע"
           "تِسْعَة"	"תשעה"
           "تَفَضَّلْ / تَفَضَّلي"	"בבקשה! התכבד/י!"
           "ثَلاث"	"שלוש"
           "ثَلاثَة"	"שלושה"
           "ثَمَانِي (ثَمَانٍ)"	"שמונֶה"
           "ثَمَانِيَة"	"שמונָה"
           "جَاءَ"	"בא"
           "جَامِعَة (ج) ات"	"אוניברסיטה"
           "(ألجَامِعَة العَرَبِيَّة"	"הליגה הערבית)"
           "جَرِيدَة (ج) جَرَائِد"	"עיתון"
           "جَلْسَة (ج) جَلَسَات"	"ישיבה"
           "جَمِيع"	"כל"
           "حَرْب (ج) حُرُوب"	"מלחמה"
           "حِزْب (ج) أحْزَاب"	"מפלגה"
           "حَضَّرَ"	"הכין"
           "حَمَّام"	"אמבטיה, מקלחת"
           "خَارِطَة, خَرِيطَة"	"מפה"
           "خُبْز"	"לֶחֶם"
           "خَلِيج"	"מפרץ"
           "خَمْس"	"חמש"
           "خَمْسَة"	"חמישה"
           "دَوْلِيّ / دُوَلِيّ"	"בין-לאומי"
           "ذَكَرَ - يَذْكُرُ"	"זָכַר, ציין"
           "رَاكِب (ج) رُكََّاب"	"נוסע"
           "رَئِيسِيّ"	"ראשי, עיקרי"
           "رِحْلَة (ج) ات"	"טיול, מסע, נסיעה"
           "رَسْمِيّ"	"רשמי"
           "سَائِح (ج) سُيَّاح"	"תייר"
           "سَاحَة"	"חצר"
           "سَاحِل (ج) سَوَاحِل"	"חוף"
           "سَاعَد א"	"עזר ל-"
           "سَاكِن (ج) سُكّان"	"תושב"
           "سَبْع"	"שבע"
           "سَبْعَة"	"שבעה"
           "سِتّ"	"שש"
           "سِتَّة"	"שישה"
           "سَمَحَ - يَسْمَحُ لِ"	"הרשה ל-"
           "سَفِير(ج) سُفَرَاء"	"שגריר"
           "سُورَة (ج) سُوَر"	"סורה(פרק, פרשה בקוראן)"
           "سَيَّارَة (ج) ات"	"מכונית"
           "سَيِّد (ج) سَادَة"	"אדון, מר"
           "سَيِّدَة (ج) ات"	"גברת"
           "شَاهَدَ א"	"צפה ב-, ראה"
           "شَاي"	"תה"
           "شُرْطَة"	"משטרה"
           "شَكَرَ - يَشْكُرُ א"	"הודה ל-"
           "صَاحِب (ج) أصْحَاب"	"חבר, בעל (רכוש)"
           "صَحِيح"	"נכון"
           "صَحِيفَة (ج) صُحُف"	"עיתון"
           "صَدِيق (ج) أصْدِقَاء"	"חבר, ידיד"
           "صَفْحَة (ج) صَفَحَات"	"עמוד (בספר)"
           "صُورَة (ج) صُوَر"	"תמונה, צורה"
           "طَائِرَة (ج) ات"	"מטוס"
           "طَالِب (ج) طُلاّب"	"סטודנט"
           "طَبَرِيَّا"	"טבריה"
           "طَعَام"	"אוכל, מזון"
           "عَادَ (ע\" يَعُودُ)"	"חזר"
           "عالَجَ א"	"טיפל ב"
           "عاقَبَ"	"העניש"
           "عَالَم"	"עולם"
           "عَام (ج) أعْوَام"	"שנה"
           "عَامِل (ج) عُمَّال"	"פועל, עובד"
           "عَزِيز"	"יקר, אהוב"
           "عَشْر"	"עשר"
           "عَشَرَة"	"עשרה"
           "عَظِيم"	"גדול, עצום"
           "عَلَّمَ"	"לימד"
           "غَادَرَ"	"עזב"
           "غَرْب"	"מערב"
           "غَزَّة"	"עזה"
           "فَجْأَةً"	"פתאום"
           "فَرِيق"	"קבוצה"
           "فَكَّر في"	"הרהר ב, חשב על"
           "فِنْجَان (ج) فَنَاجِين"	"ספל"
           "قَدَّمَ"	"הגיש"
           "قِرَاءَة"	"קריאה"
           "قِطَار"	"רכבת"
           "قَعَدَ - يَقْعُدُ"	"ישב"
           "كُرَة"	"כדור"
           "كَلِمَة (ج) كَلِمَات"	"מילה"
           "لاَعِب (ج) لاعِبُونَ"	"שחקן"
           "لَعِبَ - يَلْعَبُ"	"שיחק"
           "لِقَاء, إِلَى اللِّقَاء"	"פגישה, להתראות"
           "لَمَّا"	"כאשר"
           "لَوْن (ج) أَلْوَان"	"צֶבע"
           "مَاء"	"מים"
           "مَأْكُولاَت"	"מאכלים"
           "مُبَارَاة (ج) مُبَارَيَات"	"תחרות"
           "مُحَادَثَات"	"שיחות"
           "مَحَطََّة (ج) ات"	"תחנה"
           "مَرَّة (ج) ات"	"פעם"
           "مَشْغُول"	"עסוק, תפוס (מקום, קו...)"
           "مُشْكِلَة (ج) مَشَاكِل"	"בעיה"
           "مَشْهُور(ج) مَشْهُورُونَ"	"מפורסם"
           "مَطَار(ج) ات"	"שדה תעופה"
           "مَطْعَم (ج) مَطَاعِم"	"מסעדה"
           "مَطْلُوب (ج) مَطْلُوبُونَ"	"מבוקש, דרוש"
           "مَعًا"	"יחד"
           "مَفْهُوم"	"מובן"
           "مُقَدَّس"	"קדוש"
           "مُمَثِّل (ج) مُمَثِّلُونَ"	"נציג, שחקן (תיאטרון)"
           "مَمْنُوع"	"אסור"
           "مِنْطَقَة (ج) مَنَاطِق"	"אֵזור"
           "مُنَظَّمَة (ج) ات"	"ארגון"
           "مُهَنْدِس (ج) مُهَنْدِسُونَ"	"מהנדס"
           "مَوْضُوع (ج) مَوَاضِيع"	"נושא"
           "نَابْلُس"	"שכם"
           "نَشَرَ - يَنْشُرُ"	"פרסם"
           "نِصْف"	"חצי"
           "نَظَرَ - يَنْظُرُ إِلَى"	"הביט,הסתכל אל/ על"
           "نَظِيف"	"נקי"
           "نِهَايَة"	"סוף"
           "هَاتِف (ج) هَوَاتِف"	"טלפון"
           "هَامّ"	"חשוב"
           "هَدِيَّة"	"מתנה"
           "وَاحِد"	"אחד"
           "وَاسِع"	"מרווח, רחב"
           "وَاشِنْطُن"	"וושינגטון"
           "وَجَدَ (ע\" يَجِدُ)"	"מצא"
           "وَزِير ٱلْخَارِجِيَّة"	"שר החוץ"
           "وَزِير ٱلدَّاخِلِيَّة"	"שר הפנים"
           "وَزِير ٱلدِّفَاع"	"שר הביטחון"
           "وَزِير ٱلْمَالِيَّة"	"שר האוצר"
           "وَضَعَ - (ע\" يَضَعُ )"	"שם, הניח"
           "وَفْد (ج) وُفُود"	"משלחת"
           "وَقَعَ (ע\" يَقَعُ)"	"נפל, שָכַן (עיר, כפר), התרחש"
           "وَقَّعَ عَلَى"	"חתם על"
           }})
