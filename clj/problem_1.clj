(defn sieve [i]
  (or (zero? (mod i 3)) (zero? (mod i 5))))

(defn main []
  (printf "%d\n" (reduce + (filter sieve (range 1 1000)))))

(main)
