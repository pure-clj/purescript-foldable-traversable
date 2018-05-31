(ns Test.Main._foreign)

(defn arrayFrom1UpTo [n]
  (vec (range 1 (inc n))))

(defn arrayReplicate [n]
  (fn [x]
    (vec (repeat n x))))
