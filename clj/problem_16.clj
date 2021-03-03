(ns problem-16
  [:require strings])

(defn pow [x n] (reduce * (repeat n x)))

(println (strings/compute-sum (str (pow 2N 1000))))
