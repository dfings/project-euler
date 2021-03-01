(ns problem-25
  [:require fibonacci])

(def LIMIT (reduce * (repeat 999 (bigint 10))))

(println (+ (bigint 1) (count (take-while (partial > LIMIT) (fibonacci/big-fibonacci)))))
