(ns flashcards.utils
  "Random utilities"
  (:require [cljs.pprint :refer [pprint]]))

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

(defn answers= [ans1 ans2]
  (= (clojure.string/lower-case ans1) (clojure.string/lower-case ans2)))


(defn- weighted-rand-nth-helper
  "Helper function, pulled out to ease testing. This way we can test the logic here,
   without rand getting in the way."
  [coll weight-fn rand-val]
  (loop [rand-ptr rand-val
         [item & rest] (seq coll)]
    (let [item-weight (weight-fn item)]
      (if (>= item-weight rand-ptr)
        item
        (recur (- rand-ptr item-weight) rest)))))

(defn weighted-rand-nth
  "Like rand-nth, but gives a weighted probability to each item in the sequence. The weight of each
   is determined by calling weight-fn on the item."
  [coll weight-fn]
  (weighted-rand-nth-helper coll weight-fn (rand (reduce + (map weight-fn coll)))))

(defn choose-weighted-n
  "Choose multiple weighted elements, without duplicates"
  [n coll weight-fn]
  (loop [n n
         coll coll
         results []]
    (if (or (empty? coll) (<= n 0))
      results
      (let [result (weighted-rand-nth coll weight-fn)
            remainder (remove #{result} coll)]
        (recur (dec n) remainder (conj results result))))))


(defn spy
  "Handy debugging aid. [TODO] Should only be exposed in dev env"
  [label x]
  (prn label)
  (pprint x)
  x)

(defn spy-side
  "Handy debugging aid. [TODO] Should only be exposed in dev env"
  [label to-print to-return]
  (pprint label to-print)
  to-return)

