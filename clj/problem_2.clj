(defn gen-fib [a b]
	(if (>= b 4000000)
    []
    (cons b (gen-fib b (+ a b)))))

(defn main []
  (printf "%d\n" (reduce + (filter even? (gen-fib 1 1)))))

(main)
