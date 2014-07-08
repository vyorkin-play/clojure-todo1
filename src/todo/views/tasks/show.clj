(ns todo.views.tasks.index
  (:use [hiccup core page form])
  (:use [hiccup.bootstrap page])
  (:require [todo.views.layouts.application :refer [layout]]))

(defn page [task]
  (layout
    (str "Task #" (get task :id))
    [:div.row
     [:p.lead (get task :content)]]))
