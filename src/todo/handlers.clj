(ns todo.handlers
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [todo.database :as database]
            [todo.views.tasks :as views]))

(defroutes app-routes
  (context "/tasks" []
           (GET ["/:id", :id #"[0-9]+"] [id]
                (views/show/page (database/find-by-id id)))
           (GET "/new" [] (views/new/page))
           (GET "/" [] (views/index/page @database/tasks))
           (POST "/" [content]
                 (database/create {:content content})
                 (response/redirect "/tasks"))
           (DELETE "/" [id]
                   (database/destroy (Integer. id))
                   (response/redirect "/tasks")))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app (handler/site app-routes))
