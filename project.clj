(defproject todo "0.1.0-SNAPSHOT"
  :description "Sample todo web app, just playing"
  :url "http://github/vyorkin"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]
                 [hiccup "1.0.5"] ]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler todo.handlers/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
