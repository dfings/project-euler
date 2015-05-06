(ns strings)

(defn char-to-digit [c] (Character/digit c 10))
(defn string-to-digits [s] (map char-to-digit s))
(defn compute-sum [s] (reduce + (string-to-digits s)))
(defn compute-product [s] (reduce * (string-to-digits s)))
