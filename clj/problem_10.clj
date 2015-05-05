(ns problem-10
  [:require primes])

(defn -main [& args]
  (println (reduce + (take-while (partial > 2000000) (primes/primes)))))
