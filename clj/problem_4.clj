(def products-of-3-digit-numbers
  (for [x (range 100 1000)
        y (range 100 1000)]
   (* x y)))

(defn is-palindrome [x]
  (= (str x) (clojure.string/reverse (str x))))

(defn main []
  (println (reduce max (filter is-palindrome products-of-3-digit-numbers))))

(main)
