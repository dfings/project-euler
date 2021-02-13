(ns primes)

(defn add-to-sieve [sieve non-prime factors]
  (if (empty? factors)
    sieve
    (let [factor (first factors)
          composite (+ non-prime factor)
          existing-factors (get sieve composite [])
          sieve-after-add (assoc sieve composite (cons factor existing-factors))
          sieve-after-remove (dissoc sieve-after-add non-prime)]
      (recur sieve-after-remove non-prime (rest factors)))))

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
        (apply primes (next-prime (inc current-prime) sieve))))))
