(ns todo.views.tasks.index
  (:use [hiccup core page form element])
  (:require [todo.views.layouts.application :refer [layout]]))

(defn page [tasks]
  (letfn [(list-item [task] [:li (:content task)])]
    (layout "Task list"
      [:div.row
       [:div.panel
        [:div.panel-heading [:h3 "Tasks"]]
        [:div.panel-body
         [:ul (for [task tasks] (list-item task))]]
        [:div.panel-footer
         (link-to {:class "btn btn-primary"} "/tasks/new" "Add task")]]])))
