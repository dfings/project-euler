(ns problem-7b
  [:require primes])

(defn -main [& args]
  (println (nth (primes/primes) 10000)))
