(ns problem-20
  [:require strings])

(defn factorial [n]
  (reduce * (range (bigint 1) (bigint (inc n)))))

(defn -main [& args]
  (println (strings/compute-sum (str (factorial 100)))))
