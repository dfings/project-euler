(def NUM_COLORS 7)
(def NUM_PER_COLOR 10)
(def NUM_TO_PICK 20)
(def SUM_ALL_PICKED (- (* NUM_COLORS NUM_PER_COLOR) NUM_TO_PICK))

;; Determines if we've picked all the balls we need to from an urn.
(defn all-balls-picked [urn]
  (= (reduce + urn) SUM_ALL_PICKED))

;; Counts the number of distinct colors that have been picked from the urn.
(defn count-colors-picked [urn]
  (biginteger (count (filter #(<  % NUM_PER_COLOR) urn))))

;; Gets the next urn state giving that we're considering picking a ball at the given position
;; that has the given number currentlyl left in the urn.
(defn get-next-states [head ball-count tail]
  (if (= ball-count 0) []
    (if (empty? tail) 
      (repeat ball-count (concat head [(- ball-count 1)]))
      (repeat ball-count (concat head [(- ball-count 1)] tail)))))

;; Generates one new urn state for each possible ball that could be picked.
(defn child-gen [head ball-count tail]
  (let [next-states (get-next-states head ball-count tail)]
    (if (empty? tail)
      next-states
      (concat next-states (child-gen (concat head [ball-count]) (first tail) (rest tail))))))

;; Helper for using childGenerator on an urn. We sort the input so that compute-counts
;; can memoize properly.
(defn generate-children [urn]
  (map sort (child-gen [] (first urn) (rest urn))))

;; Tuple support.
(defn sum-tuples [tuples]
  (reduce #(map + %1 %2) tuples))

;; Computes (total-colors-picked, total-leaves) for leaves rooted at this subtree.
(def compute-counts 
  (memoize (fn [urn]
    (if (all-balls-picked urn)
      ;; If this is a leaf, then we can just count the colors directly.
      (list (count-colors-picked urn) (biginteger 1)),
      ;; Otherwise we need to sum up the values of all leaves rooted at this subtree.
      (sum-tuples (map compute-counts (generate-children urn)))))))

(defn main []
  (let [starting-urn (repeat 7 10)
        counts (compute-counts starting-urn)]
    (printf "Total colors = %s\n" (first counts))
    (printf "Total leaves = %s\n" (second counts))
    (printf "Average = %s\n" (double (reduce / counts)))))

(main)