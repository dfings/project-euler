(defn square [x]
  (* x x))

(defn divisible-by? [i ps]
  (cond (empty? ps) false
        (= (mod i (first ps)) 0) true
        (> (square (first ps)) i) false
        :else (recur i (rest ps))))

(defn get-next-prime-from [i ps]
  (if (not (divisible-by? i ps))
    i
    (recur (inc i) ps)))

(def nth-prime
  (memoize (fn [n]
    (if (= n 1) 
      2
      (get-next-prime-from 
        (+ (nth-prime (- n 1)) 1)
        (map nth-prime (range 1 n)))))))

(defn main []
  ;; Must warm up to avoid stack overflow!
  (doall (map nth-prime (range 1 10000)))
  (printf "%s\n" (nth-prime 10001)))

(main)
