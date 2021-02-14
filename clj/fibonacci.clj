(ns fibonacci)

(defn fibonacci []
  (defn gen [a b] (lazy-seq (cons a (gen b (+ a b)))))
  (gen 0 1))
