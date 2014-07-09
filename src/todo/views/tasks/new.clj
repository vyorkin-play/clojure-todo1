(ns todo.views.tasks.new
  (:use [hiccup core page form])
  (:require [todo.views.layouts.application :refer [layout]])
  (:require [todo.views.tasks.form :refer [horizontal-form]]))

(defn page [] (layout "Create task" [:div.row (horizontal-form)]))
