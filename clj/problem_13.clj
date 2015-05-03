(ns problem-13
  (:require [clojure.string :as string]))

(defn read-lines [filename]
  (string/split-lines (slurp filename)))

(defn first-chars [n obj]
  (subs (str obj) 0 n))

(defn -main [& args]
  (let [lines (read-lines (second args))]
    (printf "%s\n" (first-chars 10 (reduce + (map read-string lines))))))
