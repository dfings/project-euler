(ns problem-15)

;; First solution noticed the number of paths to any point on the lattice
;; was Pascal's triangle.

(defn sum-first-and-second [coll]
  (+ (first coll) (second coll)))

(defn pairwise-sum [prev-line]
  (if (= (count prev-line) 1)
    [(first prev-line)]
    (cons (sum-first-and-second prev-line) (pairwise-sum (drop 1 prev-line)))))

(defn next-pascal-triangle-line [prev-line]
  (cons 1 (pairwise-sum prev-line)))

(defn count-paths [n]
  (reduce max (last (take (inc (* n 2)) (iterate next-pascal-triangle-line [1])))))

;; Second solution computes the path count directly in a memoized manner.

(def count-paths2
  (memoize (fn [x y]
    (if (and (zero? x) (zero? y))
      1
      (+
        (if (zero? x) 0 (count-paths2 (dec x) y))
        (if (zero? y) 0 (count-paths2 x (dec y))))))))

(println (count-paths 20))
(println (count-paths2 20 20))
