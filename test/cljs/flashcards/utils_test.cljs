(ns flashcards.utils-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [flashcards.utils :as utils]))

(deftest lang-tools(testing "Arabic"
    (doseq [arabic-string ["إتّفق على" "إتّحاد" "إتّصال" "إتّصالات" "إستمرّ" "إستمرّ في"]]
      (is (utils/arabic? arabic-string))
      (is (utils/arabic? arabic-string (str "abc" arabic-string)))
      (is (utils/arabic? arabic-string (str arabic-string "xyz")))
      (is (utils/arabic? arabic-string (str "abc" arabic-string "xyz")))))
  (testing "Hebrew"
    (doseq [hebrew-string ["כלכלי" "כלכלה" "אישה" "נשים" "האישה"]]
      (is (utils/hebrew? hebrew-string))
      (is (utils/hebrew? hebrew-string (str "abc" hebrew-string)))
      (is (utils/hebrew? hebrew-string (str hebrew-string "xyz")))
      (is (utils/hebrew? hebrew-string (str "abc" hebrew-string "xyz")))))
  (testing "Mixed Hebrew-Arabic"
    (doseq [mixed-string ["إرهاب טרור" "إستأنف חידש" "إستمرّ נמשך"]]
      (is (utils/arabic? mixed-string))
      (is (utils/hebrew? mixed-string)))))
