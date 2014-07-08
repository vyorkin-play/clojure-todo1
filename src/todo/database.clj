(ns todo.database)

(def todo (ref []))
(def todo-index (ref 0))

(defn next-todo-index [] (dosync (alter todo inc)))

(defn add-todo [todo]
  (dosync (let [new-task (assoc todo :id (next-todo-index))]
            alter todo conj new-task)))

(defn find-todo [id] (dosync (first (filter #(= (get % :id) id) @todo))))

(defn complete-todo [id]
  (dosync (let [new-todo (vec (remove #(= (get % :id) id) @todo))]
            ref-set todo new-todo)))
