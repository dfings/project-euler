;; Non-tail recursive version, but works betwen the stack doesn't
;; get very deep.
(defn factor-with [n i]
  (loop [n n i i]
    (if (= n 1)
      []
      (if (= (mod n i) 0)
        (cons i (factor-with (/ n i) i))  ;; Can't recur here.
        (recur n (+ i 1))))))

(defn factor [n]
  (reverse (factor-with n 2)))

;; Solution using full tail recursion.
(defn get-tail-args [n i factors]
  (if (= (mod n i) 0)
    (list (/ n i) i (cons i factors))
    (list n (+ i 1) factors)))

(defn factor-tail-recursive [n]
  (loop [n n 
         i 2 
         factors []]
    (if (= n 1)
      factors
      (let [[n' i' factors'] (get-tail-args n i factors)]
        (recur n' i' factors')))))

(defn main []
  (printf "%d\n" (first (factor 600851475143)))
  (printf "%d\n" (first (factor-tail-recursive 600851475143))))

(main)
