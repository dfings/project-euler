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

(defn get-next-prime [ps]
  (get-next-prime-from (inc (last ps)) ps))

(declare primes)
(defn nth-prime [n]
  (if (= n 1) 
    2
    (get-next-prime (take (- n 1) primes))))

(def nums (iterate inc 1))
(def primes (map nth-prime nums))

(defn main []
  (println (nth-prime 10001)))

(main)
