(ns todo.views.layouts.application
  (:use [hiccup core page form])
  (:use [hiccup.bootstrap page])
  (:require [todo.config :as config]))

(def nav-items
  {:root {:name "Home" :href config/site-root-path}
   :tasks {:name "Tasks" :href "/tasks"}
   :users {:name "Users" :href "/users"}})

(defn head [title]
  [:head
   [:title title]
   (include-css "/css/bootstrap.min.css"
                "/css/bootstrap-theme.min.css")])

(defn footer []
  [:footer.footer
   [:div.container
    [:p.pull-right
     [:a {:href "#"} "><(((*>"]]
    [:p "fly away to the Mars"]]])

(defn nav-link [link]
  [:li
   (when (link :active) {:class "active"})
   [:a {:href (link :href)} (link :name)]])

(defn nav-fixed [links]
  [:div.navbar.navbar-fixed
   [:div.navbar-inner
    [:div.container
     [:a.brand {:href config/site-root-path} config/site-title]
     [:ul.nav (for [key (keys links)] (nav-link (links key)))]]]])

(defn activate-nav-item [nav active]
  (if (nil? active) nav (assoc-in nav [active :active] true)))

(defn layout [title contents active-nav]
  [:html5
   (head title)
   [:body
    (nav-fixed (activate-nav-item nav-items active-nav))
    [:div.container contents]
    (footer)
    (include-js "/js/bootstrap.min.js")]])
