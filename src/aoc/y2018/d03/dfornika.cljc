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

(defn parse-claim [claim]
  (let [split-claim (str/split claim #" ")]
    {:id (str->int (str/replace (nth split-claim 0) "#" ""))
     :x (str->int (first (str/split (nth split-claim 2) #",")))
     :y (str->int (str/replace (second (str/split (nth split-claim 2) #",")) ":" ""))
     :width (str->int (first (str/split (nth split-claim 3) #"x")))
     :height (str->int (second (str/split (nth split-claim 3) #"x")))}))
  
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
