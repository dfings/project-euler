(defn sieve [i]
  (or (= 0 (mod i 3)) (= 0 (mod i 5))))

(defn main []
  (printf "%d\n" (reduce + (filter sieve (range 1 1000)))))

(main)
