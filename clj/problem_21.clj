(ns problem-21
  [:require factor])

(def d
  (memoize (fn [n]
             (+ 1 (reduce + (factor/factors n))))))

(defn amicable [n]
  (and
   (= n (d (d n)))
   (not= n (d n))))

(println (reduce + (filter amicable (range 10000))))
