(ns problem-10
  [:require primes])

(println (reduce + (take-while (partial > 2000000) (primes/primes))))
