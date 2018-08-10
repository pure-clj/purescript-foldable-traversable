(ns Data.Traversable._foreign)

(defn- array1 [x]
  [x])

(defn- array2 [a]
  (fn [b]
    [a b]))

(defn- array3 [a]
  (fn [b]
    (fn [c]
      [a b c])))

(defn- concat2 [xs]
  (fn [ys]
    (vec (concat xs ys))))

(defn traverseArrayImpl [apply*]
  (fn [map*]
    (fn [pure]
      (fn [f]
        (fn [array]
          (letfn [(go [bot top]
                      (case (- top bot)
                        0 (pure [])
                        1 ((map* array1) (f (nth array bot)))
                        2 ((apply* ((map* array2) (f (nth array bot))))
                           (f (nth array (inc bot))))
                        3 ((apply* ((apply* ((map* array3) (f (nth array bot))))
                                    (f (nth array (inc bot)))))
                           (f (nth array (+ 2 bot))))
                        (let [pivot (-> (long (Math/floor (/ (- top bot) 4)))
                                        (* 2)
                                        (+ bot))]
                          ((apply* ((map* concat2) (go bot pivot)))
                           (go pivot top)))))]
            (go 0 (count array))))))))
