(ns playground.destructuring)

;; https://clojuredocs.org/clojure.core/destructure
(defmacro def+
  "binding => binding-form
  internalizes binding-forms as if by def."
  {:added "1.9", :special-form true, :forms '[(def+ [bindings*])]}
  [& bindings]
  (let [bings (partition 2 (destructure bindings))]
    (sequence cat 
              ['(do) 
               (map (fn [[var value]] `(def ~var ~value)) bings)
               [(mapv (fn [[var _]] (str var)) bings)]])))



(defn f [[a b c d]]
  c)

(f [1 2 3 4])
;; => 3

(let [[a b c d] [1 2 3 4]]
  c)
;; => 3

(def+ [a b c d] [1 2 3 4])
c
;; => 3

(defn g [{:keys [x y]}]
  x)
(g {:x 1 :y 2})
;; => 1

;; default values:
(defn g1 [{:keys [x y]
           :or {x 9}}]
  x)
(g1 {:y 2})
;; => 9

;; call without a (map)
(defn g2 [& {:keys [x y]
             :or   {x 9}}]
  x)
(g2 :y 2)

;; varargs
(defn g3 [a b & args]
  (count args))

(g3 1 2 3 4 5 6)
;; => 4



(defn pyprint1 [args & {:keys [sep]}] blabla)
(pyprint1 [1 2 3] :sep ",")
;; => print(1, 2, 3, sep=",")

(defn pyprint2 [& {:keys [* sep]}] blabla)
(pyprint2 :sep "," :* [1 2 3])
;; => print(1, 2, 3, sep=",")



;; logger.warning("Hello", "David", "Levy", language="en")
(warning ["Hello" "David" "Levy"] :language "en") ;; hides the 1st arg in the vector
(warning "Hello" ["David" "Levy"] :language "en") ;; ambiguous
(warning "hello" :* ["david" "levy"] :language "en")


(warning "Hello" ["David" "Levy"] :language "en")
(-> "Hello"
    (warning ["David" "Levy"] :language "en"))


