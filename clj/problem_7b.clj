(defn square [x]
  (* x x))

(defn divisible-by? [i ps]
  (cond (empty? ps) false
        (= (mod i (first ps)) 0) true
        (> (square (first ps)) i) false
        :else (recur i (rest ps))))

(defn next-prime [i ps]
  (if (not (divisible-by? i ps))
    i
    (recur (inc i) ps)))

(defn primes 
  ([] 
    (primes 2 []))
  ([current-prime all-prior]
    (let [new-prior (conj all-prior current-prime)
          next-candidate (+ current-prime 1)]
      (lazy-seq (cons
        current-prime 
        (primes (next-prime next-candidate new-prior) new-prior))))))

(defn main []
  (println (nth (primes) 10000)))

(main)
