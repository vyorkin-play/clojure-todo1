(ns todo.views
  (:use [hiccup core page form])
  (:use [hiccup.bootstrap page]))

(defn default-layout
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
       [:ul (for [x todos] [:li (:content x)])]]]))

(defn show [todo]
  (default-layout "show" (str "todo #" (get todo :id))
    [:div {:class "row"}
     [:p {:class "lead"} (get todo :content)]]))

(defn create []
  (default-layout "create" "new task"
    [:div {:class "row"}
     (form-to [:post "/todo"]
              (label "content" "Content")
              [:br]
              (text-area "content")
              [:br]
              (submit-button "create"))]))
