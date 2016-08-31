(ns flashcards.dicts.dicts
  (:require
   [alandipert.storage-atom :refer [local-storage]]
   [flashcards.dicts.arabic-basic :as arabic-basic]
   [flashcards.dicts.arabic-grade11 :as arabic-grade11]
   [flashcards.dicts.state-capitals :as state-capitals]))

(def ^:private bucket-atoms
  {:basic-arabic   (local-storage (atom {}) :buckets-arabic-basic)
   :11th-grade-arabic (local-storage (atom {}) :buckets-arabic-grade11)
   :state-capitals (local-storage (atom {}) :buckets-state-capitals)})


(defn- lookup-persisted-bucket [dictionary-name word]
  (let [store (bucket-atoms dictionary-name)]
    (get @store word 0)))

(defn persist-bucket [dictionary-name word-item]
  (swap! (bucket-atoms dictionary-name) assoc (:word word-item) (:bucket word-item))
)

(defn commit-persistent-buckets []
  ;; For now, this is a no-op.  Later, maybe persist-bucket will just remember the
  ;; actions, and the real persistence will happen here.
  )



(defn- init-dictionary [dictionary]
  (let [name (:name dictionary)]
    (update dictionary :words
            #(mapv (fn [[word translation]]
                     {:word word
                      :translation translation
                      :bucket (lookup-persisted-bucket name word)})
                   %))))

(def ^:private all-dictionaries
  {:arabic-basic (init-dictionary arabic-basic/dict)
   :arabic-grade11 (init-dictionary arabic-grade11/dict)
   :state-capitals (init-dictionary state-capitals/dict)})


(defn get-names []
  (into {} (for [[key dict] all-dictionaries]
             [key (:name dict)])))

(defn get-dictionary [name]
  (name all-dictionaries))


