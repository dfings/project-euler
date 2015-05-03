(ns factor)

(defn factor-with [n i]
  (loop [n n i i]
    (cond (= n 1) []
          (zero? (mod n i)) (lazy-seq (cons i (factor-with (/ n i) i)))
          :else (recur n (inc i)))))

(defn factor [n]
  (reverse (factor-with n 2)))
