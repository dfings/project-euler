(defn multiply-by-all-3-digit-numbers [x]
  (map (partial * x) (range 100 999)))

(def products-of-3-digit-numbers
  (reverse (sort (flatten 
    (map multiply-by-all-3-digit-numbers (range 100 999))))))

(defn is-palindrome [x]
  (= (str x) (clojure.string/reverse (str x))))

(defn main []
  (printf "%d\n" (first (filter is-palindrome products-of-3-digit-numbers))))

(main)
