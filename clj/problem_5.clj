(defn gcd [x y]
  (if (zero? y)
    x
    (recur y (mod x y))))

(defn lcm [x y]
  (/ (* x y) (gcd x y)))

(defn main []
  (println (reduce lcm (range 1 20))))

(main)
