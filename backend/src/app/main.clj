(ns app.main
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.test :as test]))

(defn response [status body & {:as headers}]
  {:status status :body body :headers headers})

(def ok (partial response 200))
(def created (partial response 201))
(def accepted (partial response 202))

(defonce database (atom {}))

(def create-user
  {:name :create-user
   :enter
   (fn [context]
     (assoc context :response "user created"))})

(def routes
  (route/expand-routes
   #{["/users" :post create-user :route-name :create-user]}))

(def service-map
  {::http/routes routes
   ::http/type :jetty
   ::http/port 8890})

(defn start []
  (http/start (http/create-server service-map)))

(defonce server (atom nil))

(defn start-dev []
  (reset! server
          (http/start (http/create-server
                       (assoc service-map
                              ::http/join? false)))))

(defn stop-dev []
  (http/stop @server))

(defn restart []
  (stop-dev)
  (start-dev))

(defn test-request [verb url]
  (test/response-for (::http/service-fn @server) verb url))

(comment

  (start-dev)

  (test-request :post "/users")

  nil)
