(ns problem-13
  (:require [clojure.string :as string]))

(defn read-lines [filename]
  (string/split-lines (slurp filename)))

(defn first-chars [n obj]
  (subs (str obj) 0 n))

(let [lines (read-lines (second args))]
  (println (first-chars 10 (reduce + (map read-string lines)))))
