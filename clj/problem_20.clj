(ns problem-20
  [:require strings])

(defn factorial [n]
  (reduce * (range 1N (bigint (inc n)))))

(println (strings/compute-sum (str (factorial 100))))
