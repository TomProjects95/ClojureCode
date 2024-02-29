(ns matcher-starter.core)

(def ^:private inf Double/POSITIVE_INFINITY)

(defn update-costs
  "Returns costs updated with any shorter paths found to curr's unvisisted
  neighbors by using curr's shortest path"
  [g costs unvisited curr]
  (println curr)
  (let [curr-cost (get costs curr)]
    (reduce-kv
      (fn [c nbr nbr-cost]
        (if (unvisited nbr)
          (update-in c [nbr] min (+ curr-cost nbr-cost))
          c))

      costs
      (get g curr))))

(defn dijkstra
  "Returns a map of nodes to minimum cost from src using Dijkstra algorithm.
  Graph is a map of nodes to map of neighboring nodes and associated cost.
  Optionally, specify destination node to return once cost is known"
  ([g src]
   (dijkstra g src nil))
  ([g src dst]
   (loop [costs (assoc (zipmap (keys g) (repeat inf)) src 0)
          curr src
          unvisited (disj (apply hash-set (keys g)) src)]
     (cond
       (= curr dst)
       (select-keys costs [dst])

       (or (empty? unvisited) (= inf (get costs curr)))
       costs

       :else
       (let [next-costs (update-costs g costs unvisited curr)
             next-node (apply min-key next-costs unvisited)]
         (recur next-costs next-node (disj unvisited next-node)))))))

;;code made by Tom
;;New scenario
(def grieg-d
     {:entrance1 {:lobby6 10},
      :lobby6    {:entrance1 10, :toilets4 5, :g0.01 5, :g0.46 5, :g0.47 5, :g0.48 5, :g0.49 5, :hallway14 8, :hallway20 8},
      :toilets4  {:lobby6 5},
      :g0.01     {:lobby6 5},
      :g0.46     {:lobby6 5, :g0.46a 3, :g0.46b 3},
      :g0.46a    {:g0.46 3},
      :g0.46b    {:g0.46 3},
      :g0.47     {:lobby6 5},
      :g0.48     {:lobby6 5},
      :g0.49     {:lobby6 5, :g0.49a 3},
      :g0.49a    {:g0.49 3},
      :hallway14 {:lobby6 8, :g0.41 5, :g0.43 5, :g0.42 5, :g0.45 5, :g0.44 5, :hallway15 8},
      :g0.41     {:hallway14 5},
      :g0.42     {:hallway14 5},
      :g0.43     {:hallway14 5},
      :g0.44     {:hallway14 5, :g0.44a 3, :g0.44b 3, :g0.44c 3},
      :g0.44a    {:g0.44 3},
      :g0.44b    {:g0.44 3},
      :g0.44c    {:g0.44 3},
      :g0.45     {:hallway14 5},
      :hallway15 {:hallway14 8, :g0.39 5, :g0.40 5, :lobby7 8},
      :g0.39     {:hallway15 5},
      :g0.40     {:hallway15 5},
      :lobby7    {:hallway15 8, :g0.36 5, :g0.38 5, :hallway18 8, :hallway16 8, :entrance2 8},
      :entrance2 {:lobby7 8},
      :g0.36     {:lobby7 5},
      :g0.38     {:lobby7 5},
      :hallway16 {:lobby7 8 :g0.34 5, :g0.35 5, :hallway17 8},
      :g0.34     {:hallway16 5},
      :g0.35     {:hallway16 5},
      :hallway17 {:hallway16 8, :g0.27 5, :g0.29 5, :g0.30 5, :g0.31 5, :g0.32 5, :g0.33 5, :g0.56 5, :lobby8 8},
      :g0.27     {:hallway17 5},
      :g0.29     {:hallway17 5},
      :g0.30     {:hallway17 5},
      :g0.31     {:hallway17 5},
      :g0.32     {:hallway17 5},
      :g0.33     {:hallway17 5},
      :g0.56     {:hallway17 5},
      :lobby8    {:hallway17 8, :g0.21 5, :g0.22 5, :g0.23 5, :g0.24 5, :g0.25 5, :g0.26 5, :g0.54 5, :hallway18 8},
      :g0.21     {:lobby8 5},
      :g0.22     {:lobby8 5},
      :g0.23     {:lobby8 5},
      :g0.24     {:lobby8 5},
      :g0.25     {:lobby8 5},
      :g0.26     {:lobby8 5},
      :g0.54     {:lobby8 5},
      :hallway18 {:lobby8 8, :g0.10 5, :g0.11 5, :g0.12 5, :g0.13 5, :g0.52 5, :lobby7 8, :hallway19 10, :hallway20 8},
      :g0.10     {:hallway18 5},
      :g0.11     {:hallway18 5},
      :g0.12     {:hallway18 5},
      :g0.13     {:hallway18 5},
      :g0.52     {:hallway18 5},
      :hallway19 {:hallway18 10, :g0.15a 5, :g0.15b 5, :g0.16 5, :g0.19 5, :g0.20 5},
      :g0.15a    {:hallway19 5},
      :g0.15b    {:hallway19 5},
      :g0.16     {:hallway19 5},
      :g0.19     {:hallway19 5},
      :g0.20     {:hallway19 5},
      :hallway20 {:hallway18 8, :lobby6 8, :g0.04 5, :g0.09 5},
      :g0.04     {:hallway20 5,},
      :g0.09     {:hallway20 5, :g0.08 3, :g0.08a 3},
      :g0.08     {:g0.09 3},
      :g0.08a    {:g0.09 3},
      })

(def lecture-building-d
    {:exit4     {:lobby5 5},
     :lobby5    {:exit4 5, :hallway12 10, :stairs3 7, :roomOL1 6},
     :roomOL1   {:lobby5 6, :hallway10 4},
     :hallway12 {:lobby5 10, :roomOL4 5, :roomOL2 5, :roomOL3 5, :hallway13 10},
     :roomOL4   {:hallway12 5},
     :roomOL2   {:hallway12 5},
     :roomOL3   {:hallway12 5, :hallway13 6},
     :hallway13 {:hallway12 10, :roomOL3 6, :roomOL6 5, :lift5 5, :roomOL7 6, :stairs4 7},
     :roomOL6   {:hallway13 5},
     :lift5     {:hallway13 5, :lift4 9},
     :roomOL7   {:hallway13 6, :hallway11 6},
     ;;floor 1
     :stairs4   {:hallway13 7, :hallway11 5},
     :hallway11 {:stairs4 5, :toilets3 5, :lift4 5, :roomOL9 5, :roomOL10 5, :roomOL7 6, :hallway10 10},
     :toilets3  {:hallway11 5},
     :lift4     {:hallway11 5, :lift5 9},
     :roomOL9   {:hallway11 5},
     :roomOL10  {:hallway11 5},
     :hallway10 {:hallway11 10, :roomOL8 5, :roomOL1 4, :lobby4 5},
     :roomOL8   {:hallway10 5},
     :lobby4    {:hallway10 5, :stairs3 7},
     :stairs3   {:lobby4 7, :lobby5 7},
     })

(def it-building-d
   {:exit3     {:hallway9 5},
    :hallway9  {:exit3 5, :room0.05 5, :room0.06 5, :room0.07 5, :room0.08 5, :room0.11 5, :lobby3 10},
    :room0.05  {:hallway9 5},
    :room0.06  {:hallway9 5},
    :room0.07  {:hallway9 5},
    :room0.08  {:hallway9 5},
    :room0.11  {:hallway9 5, :room0.12 3},
    :room0.12  {:room0.11 3},
    :lobby3    {:hallway9 10, :room0.13 5, :room0.15 5, :hallway8 10, :lift3 5},
    :lift3     {:lobby3 5, :lift2 12, :lift1 9},
    :room0.13  {:lobby3 5, :room0.14 3},
    :room0.14  {:room0.13 3},
    :room0.15  {:lobby3 5},
    :hallway8  {:lobby3 10, :stairs2 6},
    ;;floor 1
    :stairs2   {:hallway8 6, :lobby1 2, :lobby2 6},
    :stairs1   {:hallway1 5, :hallway6 7},
    :hallway1  {:stairs1 5, :room1.35 5, :room1.34 5, :hallway2 10},
    :room1.35  {:hallway1 5},
    :room1.34  {:hallway1 5},
    :hallway2  {:hallway1 10, :room1.31 5, :room1.32 5, :room1.33 5, :lobby1 10},
    :room1.31  {:hallway2 5},
    :room1.32  {:hallway2 5},
    :room1.33  {:hallway2 5, :room1.33a 3},
    :room1.33a {:room1.33 3},
    :lobby1    {:hallway2 10, :stairs2 2, :room1.30 5, :room1.13 5, :hallway3 10},
    :room1.30  {:lobby1 5},
    :room1.13  {:lobby1 5, :hallway4 3},
    :hallway3  {:lobby1 10, :lift1 5, :room1.10 5, :room1.11 5, :hallway4 6, :hallway5 10},
    :lift1     {:hallway3 5, :lift2 9, :lift3 9},
    :room1.10  {:hallway3 5},
    :room1.11  {:hallway3 5},
    :hallway4  {:hallway3 6, :room1.01 5, :exit1 5, :room1.13 3},
    :room1.01  {:hallway4 5},
    :exit1     {:hallway4 5},
    :hallway5  {:hallway3 10, :toilets1 5, :room1.04 5, :room1.05 5, :room1.06 5, :room1.07 5, :room1.08 5, :exit2 2},
    :toilets1  {:hallway5 5},
    :room1.04  {:hallway5 5},
    :room1.05  {:hallway5 5},
    :room1.06  {:hallway5 5},
    :room1.07  {:hallway5 5},
    :room1.08  {:hallway5 5, :room1.09 3},
    :room1.09  {:room1.08 3},
    :exit2     {:hallway5 2},
    ;;floor 2
    :hallway6  {:stairs1 7, :room2.41 5, :room2.42 5, :hallway7 10},
    :room2.41  {:hallway6 5},
    :room2.42  {:hallway6 5, :room2.39 3},
    :room2.39  {:room2.42 3, :room2.34 3},
    :hallway7  {:hallway6 10, :room2.38 5, :room2.34 5, :toilets2 5, :lobby2 10},
    :room2.38  {:hallway7 5},
    :room2.34  {:hallway7 5, :room2.39 3},
    :toilets2  {:hallway7 5},
    :lobby2    {:hallway7 10, :room2.31 5, :room2.30 5, :lift2 5, :stairs2 6},
    :room2.31  {:lobby2 5},
    :room2.30  {:lobby2 5},
    :lift2     {:lobby2 5, :lift1 9, :lift3 12},
    })

(def grieg-lecture-building-d
    {:entrance1   {:lobby6 10, :outsideArea 20},
     :lobby6      {:entrance1 10, :toilets4 5, :g0.01 5, :g0.46 5, :g0.47 5, :g0.48 5, :g0.49 5, :hallway14 8, :hallway20 8},
     :toilets4    {:lobby6 5},
     :g0.01       {:lobby6 5},
     :g0.46       {:lobby6 5, :g0.46a 3, :g0.46b 3},
     :g0.46a      {:g0.46 3},
     :g0.46b      {:g0.46 3},
     :g0.47       {:lobby6 5},
     :g0.48       {:lobby6 5},
     :g0.49       {:lobby6 5, :g0.49a 3},
     :g0.49a      {:g0.49 3},
     :hallway14   {:lobby6 8, :g0.41 5, :g0.43 5, :g0.42 5, :g0.45 5, :g0.44 5, :hallway15 8},
     :g0.41       {:hallway14 5},
     :g0.42       {:hallway14 5},
     :g0.43       {:hallway14 5},
     :g0.44       {:hallway14 5, :g0.44a 3, :g0.44b 3, :g0.44c 3},
     :g0.44a      {:g0.44 3},
     :g0.44b      {:g0.44 3},
     :g0.44c      {:g0.44 3},
     :g0.45       {:hallway14 5},
     :hallway15   {:hallway14 8, :g0.39 5, :g0.40 5, :lobby7 8},
     :g0.39       {:hallway15 5},
     :g0.40       {:hallway15 5},
     :lobby7      {:hallway15 8, :g0.36 5, :g0.38 5, :hallway18 8, :hallway16 8, :entrance2 8},
     :entrance2   {:lobby7 8, :outsideArea 20},
     :g0.36       {:lobby7 5},
     :g0.38       {:lobby7 5},
     :hallway16   {:lobby7 8, :g0.34 5, :g0.35 5, :hallway17 8},
     :g0.34       {:hallway16 5},
     :g0.35       {:hallway16 5},
     :hallway17   {:hallway16 8, :g0.27 5, :g0.29 5, :g0.30 5, :g0.31 5, :g0.32 5, :g0.33 5, :g0.56 5, :lobby8 8},
     :g0.27       {:hallway17 5},
     :g0.29       {:hallway17 5},
     :g0.30       {:hallway17 5},
     :g0.31       {:hallway17 5},
     :g0.32       {:hallway17 5},
     :g0.33       {:hallway17 5},
     :g0.56       {:hallway17 5},
     :lobby8      {:hallway17 8, :g0.21 5, :g0.22 5, :g0.23 5, :g0.24 5, :g0.25 5, :g0.26 5, :g0.54 5, :hallway18 8},
     :g0.21       {:lobby8 5},
     :g0.22       {:lobby8 5},
     :g0.23       {:lobby8 5},
     :g0.24       {:lobby8 5},
     :g0.25       {:lobby8 5},
     :g0.26       {:lobby8 5},
     :g0.54       {:lobby8 5},
     :hallway18   {:lobby8 8, :g0.10 5, :g0.11 5, :g0.12 5, :g0.13 5, :g0.52 5, :lobby7 8, :hallway19 10, :hallway20 8},
     :g0.10       {:hallway18 5},
     :g0.11       {:hallway18 5},
     :g0.12       {:hallway18 5},
     :g0.13       {:hallway18 5},
     :g0.52       {:hallway18 5},
     :hallway19   {:hallway18 10, :g0.15a 5, :g0.15b 5, :g0.16 5, :g0.19 5, :g0.20 5},
     :g0.15a      {:hallway19 5},
     :g0.15b      {:hallway19 5},
     :g0.16       {:hallway19 5},
     :g0.19       {:hallway19 5},
     :g0.20       {:hallway19 5},
     :hallway20   {:hallway18 8, :lobby6 8, :g0.04 5, :g0.09 5},
     :g0.04       {:hallway20 5},
     :g0.09       {:hallway20 5, :g0.08 3, :g0.08a 3},
     :g0.08       {:g0.09 3},
     :g0.08a      {:g0.09 3},
     ;;outside
     :outsideArea {:entrance1 20, :entrance2 20, :exit4 15},
     ;;lecture building
     :exit4       {:lobby5 5, :outsideArea 15},
     :lobby5      {:exit4 5, :hallway12 10, :stairs3 7, :roomOL1 6},
     :roomOL1     {:lobby5 6, :hallway10 4},
     :hallway12   {:lobby5 10, :roomOL4 5, :roomOL2 5, :roomOL3 6, :hallway13 10},
     :roomOL4     {:hallway12 5},
     :roomOL2     {:hallway12 5},
     :roomOL3     {:hallway12 6, :hallway13 6},
     :hallway13   {:hallway12 10, :roomOL3 6, :roomOL6 5, :lift5 5, :roomOL7 6, :stairs4 7},
     :roomOL6     {:hallway13 5},
     :lift5       {:hallway13 5, :lift4 9},
     :roomOL7     {:hallway13 6, :hallway11 6},
     ;;floor 1
     :stairs4     {:hallway13 7, :hallway11 5},
     :hallway11   {:stairs4 5, :toilets3 5, :lift4 5, :roomOL9 5, :roomOL10 5, :roomOL7 6, :hallway10 10},
     :toilets3    {:hallway11 5},
     :lift4       {:hallway11 5, :lift5 9},
     :roomOL9     {:hallway11 5},
     :roomOL10    {:hallway11 5},
     :hallway10   {:hallway11 10, :roomOL8 5, :roomOL1 4, :lobby4 5},
     :roomOL8     {:hallway10 5},
     :lobby4      {:hallway10 5, :stairs3 7},
     :stairs3     {:lobby4 7, :lobby5 7},
     })

(def grieg-lecture-it-building-d
     {:entrance1   {:lobby6 10, :outsideArea 20},
      :lobby6      {:entrance1 10, :toilets4 5, :g0.01 5, :g0.46 5, :g0.47 5, :g0.48 5, :g0.49 5, :hallway14 8, :hallway20 8},
      :toilets4    {:lobby6 5},
      :g0.01       {:lobby6 5},
      :g0.46       {:lobby6 5, :g0.46a 3, :g0.46b 3},
      :g0.46a      {:g0.46 3},
      :g0.46b      {:g0.46 3},
      :g0.47       {:lobby6 5},
      :g0.48       {:lobby6 5},
      :g0.49       {:lobby6 5, :g0.49a 3},
      :g0.49a      {:g0.49 3},
      :hallway14   {:lobby6 8, :g0.41 5, :g0.43 5, :g0.42 5, :g0.45 5, :g0.44 5, :hallway15 8},
      :g0.41       {:hallway14 5},
      :g0.42       {:hallway14 5},
      :g0.43       {:hallway14 5},
      :g0.44       {:hallway14 5, :g0.44a 3, :g0.44b 3, :g0.44c 3},
      :g0.44a      {:g0.44 3},
      :g0.44b      {:g0.44 3},
      :g0.44c      {:g0.44 3},
      :g0.45       {:hallway14 5},
      :hallway15   {:hallway14 8, :g0.39 5, :g0.40 5, :lobby7 8},
      :g0.39       {:hallway15 5},
      :g0.40       {:hallway15 5},
      :lobby7      {:hallway15 8, :g0.36 5, :g0.38 5, :hallway18 8, :hallway16 8, :entrance2 8},
      :entrance2   {:lobby7 8, :outsideArea 20},
      :g0.36       {:lobby7 5},
      :g0.38       {:lobby7 5},
      :hallway16   {:lobby7 8, :g0.34 5, :g0.35 5, :hallway17 8},
      :g0.34       {:hallway16 5},
      :g0.35       {:hallway16 5},
      :hallway17   {:hallway16 8, :g0.27 5, :g0.29 5, :g0.30 5, :g0.31 5, :g0.32 5, :g0.33 5, :g0.56 5, :lobby8 8},
      :g0.27       {:hallway17 5},
      :g0.29       {:hallway17 5},
      :g0.30       {:hallway17 5},
      :g0.31       {:hallway17 5},
      :g0.32       {:hallway17 5},
      :g0.33       {:hallway17 5},
      :g0.56       {:hallway17 5},
      :lobby8      {:hallway17 8, :g0.21 5, :g0.22 5, :g0.23 5, :g0.24 5, :g0.25 5, :g0.26 5, :g0.54 5, :hallway18 8},
      :g0.21       {:lobby8 5},
      :g0.22       {:lobby8 5},
      :g0.23       {:lobby8 5},
      :g0.24       {:lobby8 5},
      :g0.25       {:lobby8 5},
      :g0.26       {:lobby8 5},
      :g0.54       {:lobby8 5},
      :hallway18   {:lobby8 8, :g0.10 5, :g0.11 5, :g0.12 5, :g0.13 5, :g0.52 5, :lobby7 8, :hallway19 10, :hallway20 8},
      :g0.10       {:hallway18 5},
      :g0.11       {:hallway18 5},
      :g0.12       {:hallway18 5},
      :g0.13       {:hallway18 5},
      :g0.52       {:hallway18 5},
      :hallway19   {:hallway18 10, :g0.15a 5, :g0.15b 5, :g0.16 5, :g0.19 5, :g0.20 5},
      :g0.15a      {:hallway19 5},
      :g0.15b      {:hallway19 5},
      :g0.16       {:hallway19 5},
      :g0.19       {:hallway19 5},
      :g0.20       {:hallway19 5},
      :hallway20   {:hallway18 8, :lobby6 8, :g0.04 5, :g0.09 5},
      :g0.04       {:hallway20 5},
      :g0.09       {:hallway20 5, :g0.08 3, :g0.08a 3},
      :g0.08       {:g0.09 3},
      :g0.08a      {:g0.09 3},
      ;;outside
      :outsideArea {:entrance1 20, :entrance2 20, :exit4 15, :exit1 20},
      ;;lecture building
      :exit4       {:lobby5 5, :outsideArea 15},
      :lobby5      {:exit4 5, :hallway12 10, :stairs3 7, :roomOL1 6, :exit3 5},
      :roomOL1     {:lobby5 6, :hallway10 4},
      :hallway12   {:lobby5 10, :roomOL4 5, :roomOL2 5, :roomOL3 6, :hallway13 10},
      :roomOL4     {:hallway12 5},
      :roomOL2     {:hallway12 5},
      :roomOL3     {:hallway12 6, :hallway13 6},
      :hallway13   {:hallway12 10, :roomOL3 6, :roomOL6 5, :lift5 5, :roomOL7 6, :stairs4 7},
      :roomOL6     {:hallway13 5},
      :lift5       {:hallway13 5, :lift4 9},
      :roomOL7     {:hallway13 6, :hallway11 6},
      ;;floor 1
      :stairs4     {:hallway13 7, :hallway11 5},
      :hallway11   {:stairs4 5, :toilets3 5, :lift4 5, :roomOL9 5, :roomOL10 5, :roomOL7 6, :hallway10 10},
      :toilets3    {:hallway11 5},
      :lift4       {:hallway11 5, :lift5 9},
      :roomOL9     {:hallway11 5},
      :roomOL10    {:hallway11 5},
      :hallway10   {:hallway11 10, :roomOL8 5, :roomOL1 4, :lobby4 5},
      :roomOL8     {:hallway10 5},
      :lobby4      {:hallway10 5, :stairs3 7, :exit2 3},
      :stairs3     {:lobby4 7, :lobby5 7},
      ;;it building
      :exit3       {:hallway9 5, :lobby5 5},
      :hallway9    {:exit3 5, :room0.05 5, :room0.06 5, :room0.07 5, :room0.08 5, :room0.11 5, :lobby3 10},
      :room0.05    {:hallway9 5},
      :room0.06    {:hallway9 5},
      :room0.07    {:hallway9 5},
      :room0.08    {:hallway9 5},
      :room0.11    {:hallway9 5, :room0.12 3},
      :room0.12    {:room0.11 3},
      :lobby3      {:hallway9 10, :room0.13 5, :room0.15 5, :hallway8 10, :lift3 5},
      :lift3       {:lobby3 5, :lift2 12, :lift1 9},
      :room0.13    {:lobby3 5, :room0.14 3},
      :room0.14    {:room0.13 3},
      :room0.15    {:lobby3 5},
      :hallway8    {:lobby3 10, :stairs2 6},
      ;;floor 1
      :stairs2     {:hallway8 6, :lobby1 2, :lobby2 6},
      :stairs1     {:hallway1 5, :hallway6 7},
      :hallway1    {:stairs1 5, :room1.35 5, :room1.34 5, :hallway2 10},
      :room1.35    {:hallway1 5},
      :room1.34    {:hallway1 5},
      :hallway2    {:hallway1 10, :room1.31 5, :room1.32 5, :room1.33 5, :lobby1 10},
      :room1.31    {:hallway2 5},
      :room1.32    {:hallway2 5},
      :room1.33    {:hallway2 5, :room1.33a 3},
      :room1.33a   {:room1.33 3},
      :lobby1      {:hallway2 10, :stairs2 2, :room1.30 5, :room1.13 5, :hallway3 10},
      :room1.30    {:lobby1 5},
      :room1.13    {:lobby1 5, :hallway4 3},
      :hallway3    {:lobby1 10, :lift1 5, :room1.10 5, :room1.11 5, :hallway4 6, :hallway5 10},
      :lift1       {:hallway3 5, :lift2 9, :lift3 9},
      :room1.10    {:hallway3 5},
      :room1.11    {:hallway3 5},
      :hallway4    {:hallway3 6, :room1.01 5, :exit1 5, :room1.13 3},
      :room1.01    {:hallway4 5},
      :exit1       {:hallway4 5, :outsideArea 20},
      :hallway5    {:hallway3 10, :toilets1 5, :room1.04 5, :room1.05 5, :room1.06 5, :room1.07 5, :room1.08 5, :exit2 2},
      :toilets1    {:hallway5 5},
      :room1.04    {:hallway5 5},
      :room1.05    {:hallway5 5},
      :room1.06    {:hallway5 5},
      :room1.07    {:hallway5 5},
      :room1.08    {:hallway5 5, :room1.09 3},
      :room1.09    {:room1.08 3},
      :exit2       {:hallway5 2, :lobby4 3},
      ;;floor 2
      :hallway6    {:stairs1 7, :room2.41 5, :room2.42 5, :hallway7 10},
      :room2.41    {:hallway6 5},
      :room2.42    {:hallway6 5, :room2.39 3},
      :room2.39    {:room2.42 3, :room2.34 3},
      :hallway7    {:hallway6 10, :room2.38 5, :room2.34 5, :toilets2 5, :lobby2 10},
      :room2.38    {:hallway7 5},
      :room2.34    {:hallway7 5, :room2.39 3},
      :toilets2    {:hallway7 5},
      :lobby2      {:hallway7 10, :room2.31 5, :room2.30 5, :lift2 5, :stairs2 6},
      :room2.31    {:lobby2 5},
      :room2.30    {:lobby2 5},
      :lift2       {:lobby2 5, :lift1 9, :lift3 12},
      })