(ns problem-12
  [:require factor]) 

(def triangular-numbers (reductions + (range)))

(defn not-enough-factors [n]
  (<= (count (factor/factors n)) 500))

(defn -main [& args]
  (println (first (drop-while (partial not-enough-factors) triangular-numbers))))
