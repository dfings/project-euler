(ns problem-25
  [:require fibonacci])

(def LIMIT (reduce * (repeat 999 10N)))

(println (+ 1N (count (take-while (partial > LIMIT) (fibonacci/big-fibonacci)))))
