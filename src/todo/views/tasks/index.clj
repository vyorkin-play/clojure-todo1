(ns todo.views.tasks.index
  (:use [hiccup core page form])
  (:use [hiccup.bootstrap page])
  (:require [todo.views.layouts.application :refer [layout]]))

(defn page [tasks]
  (letfn [(list-item [task] [:li (:content task)])]
    layout "Task list"
    [:div.row
     [:p.lead
      [:ul (for [task tasks] (list-item task))]]])
  :tasks)
