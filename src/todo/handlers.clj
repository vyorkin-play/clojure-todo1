(ns todo.handlers
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.bootstrap.middleware :refer [wrap-bootstrap-resources]]
            [todo.database :as database]
            [todo.views :as views]))

(def todos [{:id "1" :content "make index & show pages look pretty"}
            {:id "2" :content "make edit page with form"}
            {:id "3" :content "try some query builder for sql with postgres adapter"}])

(defn find-todo [id]
  (first (filter #(= (get % :id) id) todos)))

(defroutes app-routes
  (context "/todo" []
           (GET ["/:id", :id #"[0-9]+"] [id] (views/show (find-todo id)))
           (GET "/new" [] (views/create))
           (GET "/" [] (views/index todos))
           (POST "/" [content] (response/redirect "/todo"))
           (DELETE "/" [id] (response/redirect "/todo")))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (-> (handler/site app-routes)
      (wrap-bootstrap-resources)))
