(ns todo.database)

(def tasks (ref []))
(def current-id (ref 0))

(defn- next-id []
  (dosync (alter current-id inc)))

(defn create [attrs]
  (dosync (alter tasks conj (assoc attrs :id (next-id)))))

(defn find-by-id [id]
  (dosync (first (filter #(= (get % :id) id) @tasks))))

(defn destroy [id]
  (dosync (let [new-tasks (vec (remove #(= (get % :id) id) @tasks))]
            ref-set tasks new-tasks)))
