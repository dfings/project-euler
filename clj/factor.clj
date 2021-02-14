(ns factor)

(defn prime-factor [n]
  (defn prime-factor-with [n i]
    (cond 
      (= n 1) []
      (zero? (mod n i)) (lazy-seq (cons i (prime-factor-with (/ n i) i)))
      :else (recur n (inc i))))
  (reverse (prime-factor-with n 2)))

(defn factors [n]
  (defn factor-with [n i]
    (let [i-squared (* i i)]
      (cond
        (> i-squared n) []
        (= i-squared n) [i]
        (zero? (mod n i)) (lazy-seq (cons i (cons (/ n i) (factor-with n (inc i)))))
        :else (recur n (inc i)))))
  (factor-with n 2))
