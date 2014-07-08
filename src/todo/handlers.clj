(ns todo.handlers
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [hiccup.bootstrap.middleware :refer [wrap-bootstrap-resources]]
            [todo.database :as database]
            [todo.views :as views]))

(defroutes app-routes
  (context "/todo" []
           (GET ["/:id", :id #"[0-9]+"] [id]
                (views/show (database/find-by-id id)))
           (GET "/new" [] (views/create))
           (GET "/" [] (views/index @database/todos))
           (POST "/" [content]
                 (database/create {:content content})
                 (response/redirect "/todo"))
           (DELETE "/" [id]
                   (database/destroy (Integer. id))
                   (response/redirect "/todo")))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (-> (handler/site app-routes)
      (wrap-bootstrap-resources)))
