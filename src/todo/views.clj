(ns todo.views
  (:use [hiccup core page])
  (:use [hiccup.bootstrap page]))

(defn default-layout
  "Default layout to be used for todo pages"
  [title heading & content]
  (html5
    [:head
     [:title title]
     (include-js  "//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js")
     (include-bootstrap)]
    [:body (fixed-layout [:div {:class "row"} [:h1 heading]] content)]))

(defn index [todos]
   (default-layout "index" "todo list"
     [:div {:class "row"}
      [:p {:class "lead"}
       [:ul (for [x todos] [:li x])]]]))

(defn show [todo]
  (default-layout "show" (str "todo #" {:title todo})
    [:div {:class "row"}
     [:p {:class "lead"} (str "todo #" {:desc todo})]]))
