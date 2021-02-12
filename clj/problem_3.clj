;; clj -Scp `clj -Spath`:. -M problem_3.clj

(ns problem-3
  [:require factor])  ;; Main solution moved here.

(println (first (factor/prime-factor 600851475143)))