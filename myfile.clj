(ns breath.core
(:require [org.clojars.cognesence.breadth-search.core :refer :all]))
(first (rest '(a X b c)))
(first (rest (rest '(a b X c))))
(first(rest(rest '(a b [X] c))))

(cons 'a (cons 'b (cons 'c () )))

(defn inc-num [x] (inc x))
(defn inc-1st [x] (inc (first x)))


(defn lmg1 [n]
  (list (+ n 3)
        (* n 2)
        (- n 7)
        ))


(def words
  '{coat (boat moat cost)
    cost (most lost coat cast)
    boat (moat coat boot)
    moat (moot most boat)
    moot (soot boot loot)
    lost (last cast loot)
    ;;...etc...
    })


(def rooms
  '{kitchen (livingroom hall)
    livingroom (study hall kitchen)
    hall (stairs livingroom kitchen study)
    study (hall livingroom)
    stairs (landing hall)
    landing (bathroom bed2 bed1 stairs)
    bathroom (landing)
    bed1 (landing)
    bed2 (landing)
    })
