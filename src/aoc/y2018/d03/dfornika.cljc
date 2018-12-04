(ns aoc.y2018.d03.dfornika
  (:refer-clojure :exclude [read-string format])
  (:require
   [aoc.utils :as u :refer [deftest read-string format]]
   [aoc.y2018.d03.data :refer [input answer-1 answer-2]]
   [clojure.string :as str]
   [clojure.test :refer [is testing]]))

(def data
  (str/split-lines input))

(defn str->int [s]
  #?(:clj  (java.lang.Integer/parseInt s)
     :cljs (js/parseInt s)))

(defn parse-claim [claim-string]
  (let [split-claim (str/split claim-string #" ")]
    {:id (str->int (str/replace (nth split-claim 0) "#" ""))
     :x (str->int (first (str/split (nth split-claim 2) #",")))
     :y (str->int (str/replace (second (str/split (nth split-claim 2) #",")) ":" ""))
     :width (str->int (first (str/split (nth split-claim 3) #"x")))
     :height (str->int (second (str/split (nth split-claim 3) #"x")))}))

(defn max-x [claim]
  (let [{:keys [x width]} claim]
    (+ x width)))

(defn max-y [claim]
  (let [{:keys [y height]} claim]
    (+ y height)))

(defn max-dims [claims]
  "Determine the maximum dimensions of the fabric.
   Fabric is square, so just return longest dimension."
  (apply max [(->> claims
                   (sort-by max-x)
                   (reverse)
                   (first)
                   (max-x))
              (->> claims
                   (sort-by max-y)
                   (reverse)
                   (first)
                   (max-y))]))

(defn make-fabric [length]
  "Creates a square 2d vector of size length x length"
  (->> 0
       (repeat length)
       (vec)
       (repeat length)
       (vec)))

(defn solve-1 []
  
)

(defn solve-2 []

)

(deftest part-1
  (is (= (str answer-1)
         (str (solve-1)))))

(deftest part-2
  (is (= (str answer-2)
         (str (solve-2)))))
