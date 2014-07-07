(ns todo.handlers
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.bootstrap.middleware :refer [wrap-bootstrap-resources]]
            [todo.database :as database]
            [todo.views :as views]))

(defn todos '("make index & show pages look pretty"
              "make edit page with form"
              "try some query builder for sql with postgres adapter"))

(defroutes app-routes
  (context "/todo" []
           (GET "/:id" [id]
                (let [todo (first (filter #(= (get % :id) id) todos))]
                  views/show id))
           (GET "/" [] (views/index todos)))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (-> (handler/site app-routes)
      (wrap-bootstrap-resources)))
