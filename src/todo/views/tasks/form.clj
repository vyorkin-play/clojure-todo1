(ns todo.views.tasks.form
  (:use [hiccup core page form]))

(defn horizontal-form []
  (form-to
    {:role "form" :class "form-horizontal"}
    [:post "/tasks"]
    [:fieldset
     [:div {:class "form-group"}
      (label :content "Content")
      (text-field {:class "form-control" :placeholder "task description"} :content)]]
    (submit-button {:class "btn btn-default"} "Create")))
