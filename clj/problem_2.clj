(ns problem-2
  [:require fibonacci])

(println (reduce + (filter even? (take-while (partial >= 4000000) (fibonacci/fibonacci)))))
