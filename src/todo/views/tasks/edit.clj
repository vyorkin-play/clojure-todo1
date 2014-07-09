(ns todo.views.tasks.edit
  (:use [hiccup core page form])
  (:require [todo.views.layouts.application :refer [layout]]))

(defn page [task]
  (layout
    (str "Edit task #" (get task :id))
    [:div.row
     [:p.lead "Nothing to see here yet"]]))
