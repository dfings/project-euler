(defn find-triple []
  (for [a (range 1 333)
        b (range a 500)
        :let [c (- 1000 a b)]
        :when (= (+ (* a a) (* b b)) (* c c))]
   (list a b c)))

(defn main []
  (let [triple (first (find-triple))]
    (printf "%s -> %s\n" triple (reduce * triple))))

(main)
