(ns flashcards.handlers-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [flashcards.handlers :as handlers]))

(deftest lang-tools(testing "Arabic"
    (doseq [arabic-string ["إتّفق على" "إتّحاد" "إتّصال" "إتّصالات" "إستمرّ" "إستمرّ في"]]
      (is (handlers/is-arabic arabic-string))
      (is (handlers/is-arabic arabic-string (str "abc" arabic-string)))
      (is (handlers/is-arabic arabic-string (str arabic-string "xyz")))
      (is (handlers/is-arabic arabic-string (str "abc" arabic-string "xyz")))))
  (testing "Hebrew"
    (doseq [hebrew-string ["כלכלי" "כלכלה" "אישה" "נשים" "האישה"]]
      (is (handlers/is-hebrew hebrew-string))
      (is (handlers/is-hebrew hebrew-string (str "abc" hebrew-string)))
      (is (handlers/is-hebrew hebrew-string (str hebrew-string "xyz")))
      (is (handlers/is-hebrew hebrew-string (str "abc" hebrew-string "xyz")))))
  (testing "Mixed Hebrew-Arabic"
    (doseq [mixed-string ["إرهاب טרור" "إستأنف חידש" "إستمرّ נמשך"]]
      (is (handlers/is-arabic mixed-string))
      (is (handlers/is-hebrew mixed-string)))))
