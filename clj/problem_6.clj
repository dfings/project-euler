(ns problem-6)

(defn square [x]
  (* x x))

(defn sum-of-squares [x]
  (reduce + (map square (range 1 (inc x)))))

(defn square-of-sum [x]
  (square (reduce + (range 1 (inc x)))))

(println (- (square-of-sum 100) (sum-of-squares 100)))
