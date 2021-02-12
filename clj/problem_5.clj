(ns problem-5)

(defn gcd [x y]
  (if (zero? y)
    x
    (recur y (mod x y))))

(defn lcm [x y]
  (/ (* x y) (gcd x y)))

(println (reduce lcm (range 1 20)))
