(ns flashcards.utils
  (:require))

(defn arabic-char? [char]
  (let [char-code (.charCodeAt char 0)]
    (or (and (<= 0x0600 char-code) (<= char-code 0x06FF))
        (and (<= 0x0750 char-code) (<= char-code 0x077F))
        (and (<= 0x08a0 char-code) (<= char-code 0x08FF)))))

(defn hebrew-char? [char]
  (let [char-code (.charCodeAt char 0)]
    (and (<= 0x00590 char-code) (<= char-code 0x05FF))))

(defn arabic? [string]
  (some arabic-char? (seq string)))

(defn hebrew? [string]
  (some hebrew-char? (seq string)))