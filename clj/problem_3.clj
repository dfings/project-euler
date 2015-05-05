(ns problem-3
  [:require factor])  ;; Main solution moved here.

;; Solution using full tail recursion.
(defn get-tail-args [n i factors]
  (if (zero? (mod n i))
    (list (/ n i) i (cons i factors))
    (list n (inc i) factors)))

(defn factor-tail-recursive [n]
  (loop [n' n i 2 factors []]
    (if (= n' 1)
      factors
      (let [[n'' i' factors'] (get-tail-args n' i factors)]
        (recur n'' i' factors')))))

;; Second solution using full tail recursion.
(defn factor-by [n i]
  (loop [n' n factor-count 0]
    (if (> (mod n' i) 0)
      (list n' (replicate factor-count i))
      (recur (/ n' i) (inc factor-count)))))

(defn factor-tail-recursive-2 [n]
  (loop [n' n i 2 factors []]
    (if (= n' 1)
      factors
      (let [[n'' new-factors] (factor-by n' i)]
        (recur n'' (inc i) (concat new-factors factors))))))

(defn -main [& args]
  (println (first (factor/prime-factor 600851475143)))
  (println (first (factor-tail-recursive 600851475143)))
  (println (first (factor-tail-recursive-2 600851475143))))
