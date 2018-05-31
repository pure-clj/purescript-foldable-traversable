(ns Data.Foldable._foreign)

(defn foldlArray [f]
  (fn [init]
    (fn [xs]
      (reduce (fn [e1 e2]
                ((f e1) e2))
              init xs))))

(defn foldrArray [f]
  (fn [init]
    (fn [xs]
      (loop [acc init
             xs* (reverse xs)]
        (if (seq xs*)
          (recur ((f (first xs*)) acc)
                 (next xs*))
          acc)))))
