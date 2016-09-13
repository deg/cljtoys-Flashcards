(ns flashcards.dicts.arabic-grade11
  (:require [flashcards.db :as DB]))

(def dict
  {:name :11th-grade-arabic
   :from-language :arabic
   :to-language :hebrew
   :answer-type :translation
   ::DB/num-buckets 5
   :words {"إتّجه"	"פנה, שם פניו אל",
           "إتّجاه"	"כיוון",
           "إتّخذ"	"נקט, קיבל החלטה",
           "إتّفق على"	"הסכים על-, בעניין-",
           "إتّحاد"	"התאחדות, פדרציה",
           "إتّصال"	"קשר, מגע",
           "إتّصالات"	"קשרים, מגעים",
           "إجتماعيّ"	"חברתי, סוציאלי",
           "إجتماع"	"פגישה, מפגש, התכנסות",
           "إجراء"	"אמצעי, צעד, הליך",
           "إجراءات"	"אמצעים, צעדים, הליכים",
           "إحتلّ"	"כבש",
           "إجتار"	"בחר",
           "إدارة"	"הנהלה",
           "إذاعت"	"שידור, תחנת שידור",
           "إرهاب"	"טרור",
           "إستأنف"	"חידש",
           "إستمرّ"	"נמשך",
           "إستمرّ في"	"המשיך ב",
           "إستنكر"	"גינה",
           "إصطدم"	"התנגש",
           "إعترف ب"	"הכיר ב, הודה ב",
           "إعتقد"	"חשב",
           "إعتقل"	"עצר",
           "إقتصاديّ"	"כלכלי",
           "إقتصاد"	"כלכלה",
           "إمرأة"	"אישה",
           "نساء"	"נשים",
           "ألمرأة"	"האישה",
           "إنتشر"	"התפשט, התפרס, נפוץ",
           "إنتظر"	"חיכה",
           "إنتقل"	"עבר",
           "إنسحب"	"נסוג",
           "إتفجر"	"התפוצץ",
           "إحتفل ب"	"חגג את",
           "إرهابيّ"	"טרוריסט",
           "إرهابيّون"	"טרורסטים",
           "إرهابيّين"	"טרורסטים",
           "أجنبيّ"	"זר",
           "أجانب"	"זרים",
           "أهخذ+פועל בעתיד"	"התחיל ל",
           "أزمة"	"משבר",
           "أزمات"	"משברים",
           "أشار إلى"	"הצביע על, רמז על",
           "أضاف"	"הוסיף",
           "أضرب عن"	"שבת מ",
           "أعرب عن"	"הביע את",
           "أغلبيّة"	"רוב",
           "أغلق"	"סגר, נעל",
           "أقلّيّة"	"מיעוט",
           "أقلّيّات"	"מיעוטים",
           "أقلّ"	"פחות מ",
           "أكّد"	"הדגיש, אישר",
           "ألمقبل"	"הבא",
           "أمن"	"ביטחון",
           "أهمّيّة"	"חשיבות",
           "أهمّ"	"חשוב יותר",
           "بذل جهودًا"	"השקיע מאמצים",
           "أسرة"	"משפחה",
           "باع"	"מכר",
           "يبيع"	"ימכור",
           "بعث"	"שלח",
           "بمنتسبة"	"לרגל",
           "تبادل"	"חילוף, התחלפות",
           "تبادل تجاريّ"	"סחר חליפין",
           "تجربة"	"נסיון",
           "تجارب"	"נסיונות",
           "ترأس"	"עמד בראש",
           "تزوّج"	"התחתן",
           "تساّم"	"קיבל לידו, לקח",
           "تمطوّر"	"התפתחות",
           "تطوّرات"	"התפתחויות",
           "تظاهر"	"הפגין",
           "تعزيز"	"חיזוק",
           "تعليق"	"פרשנות, תגובה, talkback",
           "تغيّر"	"השתנה",
           "تفاصيل"	"פרטים",
           "تقرير"	"דו'ח",
           "تقارير"	"דו'חות",
           "توجّه إلى"	"פנה אל",
           "توقّع"	"ציפה ל",
           "تجاريّ"	"מסחרי",
           "تورة"	"מהפכה",
           "جدير بالذّكر أنّء"	"ראוי לציין ש",
           "جوّيّ"	"אווירי",
           "جوّ"	"אוויר",
           "جيش"	"צבא",
           "جنديّ"	"חייל",
           "حنود"	"חיילים",
           "حاجز"	"מחסום",
           "حواجز"	"מחסומים",
           "حادث"	"אירוע, מקרה, תאונה",
           "حوادث"	"אירועים, מקרים, תאונות",
           "حارس"	"שומר, מאבטח",
           "حرّاس"	"שומרים, מאבטחים",
           "حراسة"	"שמירה",
           "حاول"	"ניסה",
           "محاولة"	"ניסיון",
           "حتّى"	"עד ש, כדי ש",
           "حدث"	"קרה, ארע",
           "حدّ"	"גבול",
           "حدود"	"גבולות",
           "حضر"	"נכח ב",
           "حقّ"	"זכות",
           "حقوق"	"זכויות",
           "حل"	"פתרון",
           "حلول"	"פתרונות",
           "حواليّ"	"בערך, בקירוב",
           "حيث"	"מקום, שם",
           "حماية"	"שמירה, הגנה",
           "خارج"	"מחוץ ל",
           "ألخارج"	"חו'ל",
           "خبير"	"מומחה, בעל נסיון",
           "خبراء"	"מומחים, בעלי נסיון",
           "خطوة"	"צעד",
           "خطوات"	"צעדים",
           "داخل"	"בתוך",
           "دافع عن"	"הגן על",
           "دائمًا"	"תמיד",
           "دمّر"	"הרס, הרחיב",
           "دفاع"	"הגנה",
           "ذلك"	"ההוא זה/זאת",
           "وذلك"	"וזאת",
           "لذلك"	"לכן",
           "بعد ذلك"	"אחר כך",
           "رحّب ب"	"קיבל את – בסבר פנים יפות",
           "رغم"	"למרות",
           "رفض"	"דחה, סירב",
           "رفع"	"הרים",
           "رئيس الأركان"	"הרמטכ'ל",
           "سابق"	"קודם",
           "سائح"	"תייר",
           "سيّاح"	"תיירים",
           "سياحة"	"תיירות",
           "سبب"	"סיבה",
           "أسباب"	"סיבות",
           "بسبب"	"בגלל, בשל",
           "سريع"	"מהיר",
           "سمح ل"	"הרשה ל",
           "سلطة"	"שלטון, שליטה, רשות",
           "سِفارة"	"שגרירות",
           "سَفارة"	"שגרירות",
           "سفارات"	"שגרירויות",
           "سلاح"	"נשק",
           "أسلحة"	"נשקים",
           "شأن"	"עניין",
           "شؤون"	"עניינים",
           "شغل منصبًا"	"עבד במשרה",
           "شقيق"	"אח ביולוגי",
           "أشقّاء"	"אחים ביולוגים",
           "شكّل"	"הרכיב, היווה",
           "شكل"	"צורה, אופן",
           "شغل"	"עבודה",
           "شارك"	"השתתף",
           "صاروخ"	"טיל",
           "صواريخ"	"טילים",
           "ضدّ"	"נגד",
           "طوّر"	"פיתח",
           "طيّار"	"טייס",
           "طيّارون"	"טייסים",
           "طيّراين"	"טייסים",
           "طيران"	"תעופה",
           "عاد"	"חזר,שב",
           "يعود"	"יחזור,ישוב",
           "عودة"	"חזרה, שיבה",
           "عاش"	"חי",
           "يعيش"	"יחיה",
           "عبّر عن"	"הביע, ביטא את",
           "عدد"	"מספר, גיליון של עיתון",
           "أعداد"	"מספרים, גליונים של עיתון"
           "عدوّ"	"אויב",
           "أعداء"	"אוייבים",
           "عرض"	"הציג, הציע את ל",
           "عسكريّ"	"צבאי",
           "عيّن"	"מינה את- ל-",
           "فترة"	"תקופה",
           "فقط"	"רק, בלבד",
           "فكرة"	"רעיון",
           "في أعقاب"	"בעקבות",
           "في نطاق"	"במסגרת",
           "قابل"	"פגש, ראיין",
           "مقابلة"	"פגישה, ראיון",
           "قام بجولة"	"ערך סיור",
           "قائد"	"מפקד, מנהיג",
           "قادة"	"מפקדים, מנהיגים",
           "قضية"	"בעיה, תביעה משפטית",
           "قضايا"	"בעיות, תביעות משפטיות",
           "قوّيّ"	"חזק",
           "قطاع غزّة"	"רצועת עזה",
           "قوّة"	"כוח",
           "قوّات"	"כוחות",
           "كافح"	"נאבק ב",
           "كذلك"	"כמו כן",
           "مع ذلك"	"עם זאת, למרות זאת",
           "كما"	"כמו כן, כמו ש",
           "لجنة"	"ועדה",
           "لجان"	"ועידות",
           "لدى"	"אצל",
           "لقاء"	"פגישה",
           "مجال"	"תחום",
           "مجالات"	"תחומים"}})
