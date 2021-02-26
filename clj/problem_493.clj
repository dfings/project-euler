(ns problem-493)

(def NUM_COLORS 7)
(def NUM_PER_COLOR 10)
(def NUM_TO_PICK 20)
(def SUM_ALL_PICKED (- (* NUM_COLORS NUM_PER_COLOR) NUM_TO_PICK))

;; Determines if we've picked all the balls we need to from an urn.
(defn all-balls-picked [urn]
  (= (reduce + urn) SUM_ALL_PICKED))

;; Counts the number of distinct colors that have been picked from the urn.
(defn count-colors-picked [urn]
  (bigint (count (filter 
    (fn [color] (< color NUM_PER_COLOR))
    urn))))

;; Returns the urn state after the given color is picked.
(defn pick [urn color] 
  (map-indexed 
    (fn [idx, count] 
      (if (= idx color) 
        (dec count) 
        count))
    urn))

;; Tuple support.
(defn sum-tuples [tuples]
  (reduce #(map + %1 %2) tuples))

;; Computes (total-colors-picked, total-leaves) for leaves rooted at this subtree.
(def urn-stats 
  (memoize (fn [urn]
    (if (all-balls-picked urn)
      ;; If this is a leaf, then we can just count the colors directly.
      (list (count-colors-picked urn) (bigint 1)),
      ;; Otherwise we need to sum up the values of all leaves rooted at this subtree.
      (sum-tuples 
        (mapcat 
          (fn [color] 
            (map 
              (fn [_] (urn-stats (sort (pick urn color))) )  ;; sort to get more cache hits
              (range 0 (nth urn color))))
          (range 0 NUM_COLORS)))))))

(let [starting-urn (repeat NUM_COLORS NUM_PER_COLOR)
      counts (urn-stats starting-urn)]
  (printf "Total colors = %s\n" (first counts))
  (printf "Total leaves = %s\n" (second counts))
  (printf "Average = %s\n" (double (reduce / counts))))
