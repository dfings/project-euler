(ns problem-16)

(defn pow [x n] (reduce * (repeat n x)))
(defn char-to-digit [c] (Character/digit c 10))
(defn string-to-digits [s] (map char-to-digit s))
(defn compute-sum [s] (reduce + (string-to-digits s)))

(defn -main [& args]
  (println (compute-sum (str (pow (bigint 2) 1000)))))
