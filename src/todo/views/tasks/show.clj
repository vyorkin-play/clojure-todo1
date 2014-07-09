(ns todo.views.tasks.show
  (:use [hiccup core page form])
  (:require [todo.views.layouts.application :refer [layout]]))

(defn page [task]
  (layout
    (str "Task #" (get task :id))
    [:div.row
     [:p.lead (get task :content)]]))
