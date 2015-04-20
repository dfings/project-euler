(defn divide-if-factor [x i] 
  (if (zero? (mod x i)) (/ x i) x))

(defn lcm [x y]
  (loop [x' x y' y i 2 factors []]
    (if (= 1 (max x' y'))
      (reduce * factors)
      (let [x'' (divide-if-factor x' i)
            y'' (divide-if-factor y' i)
            i' (if (and (= x' x'') (= y' y'')) (inc i) i)
            factor (if (= i i') [i] [])]
        (recur x'' y'' i' (concat factor factors))))))

(defn main []
  (printf "%s\n" (reduce lcm (range 1 20))))

(main)
