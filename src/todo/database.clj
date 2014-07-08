(ns todo.database)

(def todos (ref []))
(def current-id (ref 0))

(defn next-id []
  (dosync (alter current-id inc)))

(defn create [attrs]
  (dosync (alter todos conj (assoc attrs :id (next-id)))))

(defn find-by-id [id]
  (dosync (first (filter #(= (get % :id) id) @todos))))

(defn destroy [id]
  (dosync (let [new-todos (vec (remove #(= (get % :id) id) @todos))]
            ref-set todos new-todos)))
