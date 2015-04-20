(defn is-factor [fac n] (zero? (mod n fac)))

(defn lcm-helper [x y i]
  (let [x' (if (is-factor i x) (/ x i) x)
        y' (if (is-factor i y) (/ y i) y)]
    (list x' y' (if (or (not= x x') (not= y y')) [i] []))))

(defn lcm [x y]
  (loop [x' x y' y i 2 factors []]
    (if (= 1 (max x' y'))
      (reduce * factors)
      (let [[x'' y'' factor] (lcm-helper x' y' i)
            i' (if (empty? factor) (inc i) i)]
        (recur x'' y'' i' (concat factor factors))))))

(defn main []
  (printf "%s\n" (reduce lcm (range 1 20))))

(main)
