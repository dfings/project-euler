(defn add-to-sieve [sieve non-prime factors]
  (if (empty? factors)
    sieve
    (let [factor (first factors)
          composite (+ non-prime factor)
          existing-factors (get sieve composite [])
          sieve-add (assoc sieve composite (cons factor existing-factors))
          sieve-remove (dissoc sieve-add non-prime)]
      (recur sieve-remove non-prime (rest factors)))))

(defn next-prime [i sieve]
  (let [factors (get sieve i)]
    (if (= factors nil)
      (list i (assoc sieve (* i i) [i]))
      (recur (inc i) (add-to-sieve sieve i factors)))))

(defn primes 
  ([] 
    (apply primes (next-prime 2 {})))
  ([current-prime sieve]
      (lazy-seq (cons
        current-prime 
        (apply primes (next-prime (+ current-prime 1) sieve))))))

(defn main []
  (printf "%s\n" (nth (primes) 10000)))

(main)
