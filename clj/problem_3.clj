;; Non-tail recursive version, but works betwen the stack doesn't
;; get very deep.
(defn factor-with [n i]
  (loop [n n i i]
    (cond (= n 1) []
          (= (mod n i) 0) (cons i (factor-with (/ n i) i))  ;; Can't recur here.
          :else (recur n (+ i 1)))))

(defn factor [n]
  (reverse (factor-with n 2)))

;; Solution using full tail recursion.
(defn get-tail-args [n i factors]
  (if (= (mod n i) 0)
    (list (/ n i) i (cons i factors))
    (list n (+ i 1) factors)))

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
      (recur (/ n' i) (+ factor-count 1)))))

(defn factor-tail-recursive-2 [n]
  (loop [n' n i 2 factors []]
    (if (= n' 1)
      factors
      (let [[n'' new-factors] (factor-by n' i)]
        (recur n'' (+ i 1) (concat new-factors factors))))))

(defn main []
  (printf "%d\n" (first (factor 600851475143)))
  (printf "%d\n" (first (factor-tail-recursive 600851475143)))
  (printf "%d\n" (first (factor-tail-recursive-2 600851475143))))

(main)
