;; $ alias runclojure='clj -Scp `clj -Spath`:. -M'
;; $ runclojure problem_1.clj
(ns problem-1)

(defn sieve [i]
  (or (zero? (mod i 3)) (zero? (mod i 5))))

(println (reduce + (filter sieve (range 1 1000))))
