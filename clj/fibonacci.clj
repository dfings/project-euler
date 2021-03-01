(ns fibonacci)

(defn fibonacci []
  (defn gen [a b] (lazy-seq (cons a (gen b (+ a b)))))
  (gen 1 1))

(defn big-fibonacci []
  (defn gen [a b] (lazy-seq (cons a (gen b (+ a b)))))
  (gen (bigint 1) (bigint 1)))
