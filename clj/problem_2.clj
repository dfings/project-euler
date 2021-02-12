(ns problem-2)

(defn gen-fib [a b]
	(if (>= b 4000000)
    []
    (cons b (gen-fib b (+ a b)))))

(println (reduce + (filter even? (gen-fib 1 1))))