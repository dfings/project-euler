(ns problem-7c
  [:require primes])

(defn -main [& args]
  (println (nth (primes/primes) 10000)))
