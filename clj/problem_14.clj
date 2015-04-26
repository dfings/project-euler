(defn get-next [n]
  (if (even? n)
    (/ n 2) 
    (+ (* n 3) 1)))

(def collatz-length (memoize (fn [n]
  (if (= n 1) 
    1 
    (+ 1 (collatz-length (get-next n)))))))

(defn print-length [n]
  (printf "%s %s\n" (collatz-length n) n))

(defn main []
  (print-length (apply max-key collatz-length (range 1 1000001))))

(main)
