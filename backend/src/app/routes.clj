(ns app.routes)

(defn respond-hello [request]
  {:status 200 :body "Hello World!"})

(def routes
  #{["/greet" :get respond-hello :route-name :greet]})
