(ns problem-9)

(def triple (first
  (for [a (range 1 333)
        b (range a 500)
        :let [c (- 1000 a b)]
        :when (= (+ (* a a) (* b b)) (* c c))]
   (vector a b c))))

(defn -main [args]
  (printf "%s -> %s\n" triple (reduce * triple)))
