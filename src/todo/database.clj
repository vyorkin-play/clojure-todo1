(ns todo.database)

(def todo (ref []))
(def todo-index (ref 0))

(defn next-todo-index (dosync (alter todo inc)))

(defn add-todo [todo]
  (let [new-task (assoc todo :id (next-todo-index))]
   dosync (alter todo conj new-task)))

(defn find-todo [id]
  (first (filter #(= (get % :id) id) @todo)))

(defn complete-todo [id]
  (let [new-todo (vec (remove #(= (get % :id) id) @todo))]
   dosync (ref-set todo new-todo)))
