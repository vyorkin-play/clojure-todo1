(ns todo.handlers
  (:require [ring.util.response :as response]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [todo.database :as database]
            [todo.views.tasks.index :as tasks-index]
            [todo.views.tasks.new :as tasks-new]
            [todo.views.tasks.edit :as tasks-edit]
            [todo.views.tasks.show :as tasks-show]
            [todo.views.welcome.index :as welcome-index]))

(defroutes app-routes
  (context "/tasks" []
           (GET ["/:id", :id #"\d+"] [id]
                (tasks-show/page
                  (database/find-by-id (Integer. id))))
           (DELETE ["/:id", :id #"\d+"] [id]
                   (database/destroy (Integer. id))
                   (response/redirect "/tasks"))
           (GET "/new" []
                (tasks-new/page))
           (GET "/" []
                (tasks-index/page @database/tasks))
           (POST "/" [content]
                 (database/create {:content content})
                 (response/redirect "/tasks")))
  (GET "/" [] (welcome-index/page))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app (handler/site app-routes))
