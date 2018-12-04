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
  "Takes a string representation of a claim and returns a map"
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

(defn inc-index [v idx]
  "Increment a single element in a vector of numbers v, at index idx"
  (update-in v [idx] inc))

(defn inc-range [v min max]
  "Increment a range of elements in a vector of numbers v, from indices min to max"
  (reduce inc-index v (range min max)))

(defn stake-claim [fabric claim]
  "Increments values on fabric for area covered by claim"
  (loop [fabric fabric
         y (claim :y)]
    (if (not (>= y (+ (claim :y) (claim :height))))
      (recur (update-in fabric [y] #(inc-range % (claim :x) (+ (claim :x) (claim :width))))
             (inc y))
      fabric)))

(defn count-double-claims-in-row [fabric-row]
  (reduce #(if (>= %2 2) (inc %) %) fabric-row))

(defn solve-1 []
  (def claims (map parse-claim data))
    
  (def fabric
    (-> claims
        (max-dims)
        (make-fabric)))
    
  (def claimed-fabric
    (reduce stake-claim fabric claims))

  (reduce + (map count-double-claims-in-row claimed-fabric)))

(defn solve-2 []

)

(deftest part-1
  (is (= (str answer-1)
         (str (solve-1)))))

(deftest part-2
  (is (= (str answer-2)
         (str (solve-2)))))
