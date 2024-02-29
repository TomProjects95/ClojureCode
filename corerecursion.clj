(ns recursion.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))


(defn tfact
  ([n] (tfact n 1))
  ([n r] (if (zero? n) r
                       (tfact (dec n) (* r n)))
    ))

(defn length [lis]
  (if (empty? lis)
    0
    (inc (length (rest lis)))
    ))

(defn length1 [lis]
  (if-not (seq lis)
    0
    (inc (length1 (rest lis)))
    ))

(defn length2 [lis]
  (if-not (first lis)
    0
    (inc (length2 (rest lis)))
    ))

(defn sum-upto [n]
  (if (zero? n)
    0
    (+ n (sum-upto (dec n)))
    ))

(defn fact [n]
  (if (zero? n)
    1
    (* n (fact (dec n)))
    ))

(defn sum-list [lis]
  (if (empty? lis)
    0
    (+ (first lis) (sum-list (rest lis)))
    ))

(defn sum-list2 [lis]
  (if-not (seq lis)
    0
    (+ (first lis) (sum-list2 (rest lis)))
    ))

(defn spam-all [lis]
  (if (empty? lis)
    ()
    (cons 'spam (spam-all (rest lis)))
    ))

(defn spam-all2 [lis]
  (if-not (seq lis)
    ()
    (cons 'spam (spam-all2 (rest lis)))
    ))

(defn spam2 [lis]
  (cond
    (empty? lis)
    nil

    (number? (first lis))
    (cons 'spam (spam2 (rest lis)))

    :else
    (cons (first lis) (spam2 (rest lis)))
    ))

(defn f2 [n]
  (reduce * (map inc (range n))))

(defn f3 [n]
  (loop [n n
         r 1
         ]
    (if (zero? n) r
                  (recur (dec n) (* r n)))
    ))

(defn foo [x]
  (inc (+ [x] 1)))

(defn lon [lis1 lis2]
  (if (> (count lis1) (count lis2))
    lis1
    lis2))
