(ns Data.FunctorWithIndex._foreign)

(defn mapWithIndexArray [f]
  (fn [xs]
    (vec
     (map-indexed (fn [i e]
                    ((f i) e))
                  xs))))
