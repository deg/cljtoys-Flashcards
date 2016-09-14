(ns flashcards.dicts.arabic-11
  (:require [flashcards.db :as DB]))

(def dict
  {:name :11th-grade-arabic-cms
   :source "http://cms.education.gov.il/EducationCMS/Units/Tochniyot_Limudim/LashonAravit/MaagareyMilim/lefiAlefBetAravi/Kita11.htm"
   :from-language :arabic
   :to-language :hebrew
   :answer-type :translation
   ::DB/num-buckets 5
   :words {"إتّحاد"	"התאחדות, פדרציה"
           "إتّخذ"	"נקט, קיבל (החלטה)"
           "إتّصال (ج) ات"	"קשר, מגע"
           "إتّفق على"	"הסכים על-, בעניין-"
           "إجتماع"	"פגישה, מפגש, התכנסות"
           "إجتماعيّ"	"חברתי, סוציאלי"
           "إجراء (ج) ات"	"אמצעי, צעד, הליך"
           "إحتفل"	"חגג"
           "إحتلّ"	"כבש"
           "أخذ + פועל בעתיד"	"התחיל ל..."
           "إدارة"	"הנהלה"
           "إرْهاب"	"טרור"
           "إرْهابِيّ (ج) - ُون/ -ِين"	"טרוריסט"
           "أزمة (ج) أَزَمَات"	"משבר"
           "إستأنف"	"חידש"
           "إستمرّ"	"נמשך"
           "أشار إلى"	"הצביע על-, רמז על-"
           "إصطدم"	"התנגש"
           "أضاف"	"הוסיף"
           "أضرب عن"	"שבת מ-"
           "إعتقد"	"חשב"
           "إعتقل"	"עצר"
           "أعرب عَنْ"	"הביע את-"
           "أغلق"	"סגר, נעל"
           "إقتصاد"	"כלכלה"
           "إقتصاديّ"	"כלכלי"
           "أكّد"	"הדגיש, אישר"
           "المقبل"	"הבא"
           "أمر"	"ُ ציווה"
           "أمْن"	"ביטחון"
           "إنتشر"	"התפשט, התפרס, נפוץ"
           "إنتظر"	"חיכה"
           "إنسحب"	"נסוג"
           "إنفجر"	"התפוצץ"
           "أهمّيّة"	"חשיבות"
           "أُولَى"	"ראשונה"
           "بالنّسبة إلى"	"ביחס ל-"
           "بذل جهودا"	"השקיע מאמצים"
           "بسبب"	"בגלל, בשל-"
           "بعث"	"َ שלח"
           "بمناسبة"	"לרגל"
           "تجاريّ"	"מסחרי"
           "ترأّس"	"עמד בראש"
           "تزوّج"	"התחתן"
           "تطوُّر (ج) ات"	"התפתחות"
           "تظاهر"	"הפגין"
           "تغيّر"	"השתנה"
           "تفاصيل"	"פרטים"
           "تقرير (ج) تقارير"	"דו\"ח"
           "توجّه إلى"	"פנה אל"
           "توقّع א"	"ציפה ל-"
           "جنديّ (ج) جنود"	"חייל"
           "جَوّ"	"אוויר"
           "جَوّيّ"	"אווירי"
           "جيش"	"צבא"
           "حاجز (ج) حواجز"	"מחסום"
           "حادث (ج) حوادث"	"אירוע, מקרה, תאונה"
           "حاول"	"ניסה"
           "حدّ (ج) حدود"	"גבול, גבולות"
           "حدث"	"קרה, ארע"
           "حضر-ُ א"	"נכח"
           "حقّ (ج) حقوق"	"זכות"
           "حَلَّ"	"ُ פתר"
           "حلّ (ج) حلول"	"פתרון"
           "حَوَالَيْ"	"בערך, בקירוב"
           "حَيْثُ"	"מקום שם"
           "خارج"	"מחוץ ל-"
           "خطوة (ج) خُطُوات"	"צעד"
           "داخل"	"בתוך"
           "دافع عن"	"הגן על-"
           "دفاع"	"הגנה"
           "رئيس الأركان"	"הרמטכ\"ל"
           "رحّب ب"	"קיבל את- בסבר פנים יפות"
           "رفض"	"ُ דחה, סירב"
           "سابق"	"קודם"
           "سبب (ج) أسباب"	"סיבה"
           "سريع"	"מהיר"
           "سفارة (ج) ات"	"שגרירות"
           "سلاح (ج) أسلحة"	"נשק"
           "سُلطة"	"שלטון, שליטה, רשות"
           "سمح - َ ل"	"הרשה ל-"
           "شارَكَ"	"השתתף"
           "شَأْن (ج) شُؤون"	"עניין"
           "شخص (ج) أشخاص"	"איש"
           "صحفيّ (ج) - ُونَ/ -ِينَ"	"עיתונאי"
           "صرّح بأنّ"	"הצהיר ש-"
           "ضدّ"	"נגד"
           "طوّر"	"פיתח"
           "طَيّار (ج) - ُونَ/ -ِينَ"	"טייס"
           "عِدّة"	"מספר"
           "عدد (ج) أعداد"	"מספר, גיליון (של עיתון)"
           "عسكريّ"	"צבאי"
           "عيّن אא"	"מינה את- ל-"
           "فترة"	"תקופה"
           "فَقَطْ"	"רק, בלבד"
           "في نطاق"	"במסגרת"
           "قائد (ج) قادة"	"מפקד, מנהיג"
           "قام بجولة"	"ערך סיור"
           "قضيّة (ج) قضايا"	"בעיה, תביעה משפטית"
           "قطاع غزّة"	"רצועת עזה"
           "قوّة (ج) ات"	"כוח"
           "لجنة (ج) لجان"	"ועדה"
           "لدى"	"אצל"
           "لقاء"	"פגישה"
           "مُؤتمر صحفيّ"	"מסיבת עיתונאים"
           "مؤتمر قمّة"	"ועידת פסגה"
           "مؤخّرًا"	"לאחרונה"
           "مباحثات"	"דיונים"
           "متحدّث باسم"	"דובר בשם"
           "مِثْل"	"כמו"
           "مجال (ج) ات"	"תחום"
           "مُجْتَمَع"	"חברה"
           "مختلف"	"שונה"
           "مدرسة ابتدائيّة"	"בית ספר יסודי"
           "مدرسة إعداديّة"	"חטיבת ביניים"
           "مدرسة ثانويّة"	"בית ספר תיכון"
           "مراسل"	"כתב"
           "مرحلة (ج) مراحل"	"שלב"
           "مستشار (ج) - ُونَ/ -ِينَ"	"יועץ (קנצלר)"
           "مستقبل"	"עתיד"
           "مُسَلََّح (ج) - ونَ/ -ِينَ"	"חמוש, מזוין"
           "مشروع (ج) مشاريع"	"פרויקט, תכנית"
           "مصدر (ج) مصادر"	"מקור"
           "مُعارضة"	"אופוזיציה"
           "مَعْلومات"	"ידיעות, אינפורמציה"
           "مفاوضات"	"משא ומתן"
           "مقابل"	"תמורת, לעומת-"
           "من أجل"	"למען"
           "مناقشات"	"דיונים"
           "منْدوب (ج) - ونَ/ -ِينَ"	"נציג"
           "مُنذ"	"מאז, מזה"
           "ناطق بلسان"	"דובר מטעם"
           "ناقش"	"דן"
           "نَجَاح"	"הצלחה"
           "نقل"	"העביר"
           "هدف (ج) أهداف"	"מטרה"
           "واصل א/ في"	"המשיך ב-"
           "وكالة (ج) ات"	"סוכנות"
           "وَضْع"	"מצב"
           "وطن"	"מולדת"
           "وطنيّ"	"לאומי"
           "وقف إطلاق النار"	"הפסקת אש"

           }})
