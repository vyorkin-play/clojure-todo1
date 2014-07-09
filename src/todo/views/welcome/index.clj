(ns todo.views.welcome.index
  (:use [hiccup core page form])
  (:require [todo.views.layouts.application :refer [layout]]))

(defn page []
  (layout
    "Home"
    [:div.hero-unit
     [:h1 "Clojure todo list"]
     [:p "CRUD you fucking tasks & die"]]))
