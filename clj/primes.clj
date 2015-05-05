(ns primes)

;; Version 1 doesn't use atoms or swap!

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
        (apply primes (next-prime (inc current-prime) sieve))))))

;; Version 2 uses atoms and swap!

(defn add-to-sieve2 [sieve non-prime factors]
  (doseq [factor factors]
    (let [composite (+ non-prime factor)
          existing-factors (get @sieve composite [])]
      (do
        (swap! sieve assoc composite (cons factor existing-factors))
        (swap! sieve dissoc sieve non-prime)))))

(defn next-prime2 [i sieve]
  (let [factors (get @sieve i)]
    (if (= factors nil)
      (do 
        (swap! sieve assoc (* i i) [i])
        i)
      (do
        (add-to-sieve2 sieve i factors)        
        (recur (inc i) sieve)))))

(defn primes2
  ([] 
    (let [sieve (atom {})]
      (primes2 (next-prime2 2 sieve) sieve)))
  ([current-prime sieve]
      (lazy-seq (cons
        current-prime 
        (primes2 (next-prime2 (inc current-prime) sieve) sieve)))))
