(ns assessment.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))


(def ops

  '{pickup {:pre ( (agent ?agent)

                   (manipulable ?obj)

                   (at ?agent ?place)

                   (on ?obj   ?place)

                   (holds ?agent nil)

                   )

            :add ((holds ?agent ?obj))

            :del ((on ?obj   ?place)

                   (holds ?agent nil))

            :txt (pickup ?obj from ?place)

            :cmd [grasp ?obj]

            }

    drop    {:pre ( (at ?agent ?place)

                    (holds ?agent ?obj)

                    (:guard (? obj))

                    )

             :add ( (holds ?agent nil)

                    (on ?obj   ?place))

             :del ((holds ?agent ?obj))

             :txt (drop ?obj at ?place)

             :cmd [drop ?obj]

             }

    move    {:pre ( (agent ?agent)

                    (at ?agent ?p1)

                    (connects ?p1 ?p2)

                    )

             :add ((at ?agent ?p2))

             :del ((at ?agent ?p1))

             :txt (move ?p1 to ?p2)

             :cmd [move ?p2]

             }

    })
(def state1

  '#{(at R table)

     (on book table)

     (on spud table)

     (holds R nil)

     (connects table bench)

     (manipulable book)

     (manipulable spud)

     (agent R)

     })