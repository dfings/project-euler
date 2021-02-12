(ns problem-16
  [:require strings])

(defn pow [x n] (reduce * (repeat n x)))

(println (strings/compute-sum (str (pow (bigint 2) 1000))))
