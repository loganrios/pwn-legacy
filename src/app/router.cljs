(ns app.router
  (:require [bidi.bidi :as b]
            [pushy.core :as pushy]
            [re-frame.core :refer [dispatch]]))

(def routes ["/" {"" :home
                  "login" :login
                  "work/" {[:slug] :work}}])

(def history
  (let [dispatch #(dispatch [:set-active-page {:page (:handler %)
                                               :slug (get-in % [:route-params :slug])}])
        match #(b/match-route routes %)]
    (pushy/pushy dispatch match)))

(defn start!
  []
  (pushy/start! history))

(def url-for (partial b/path-for routes))

(defn set-token!
  [token]
  (pushy/set-token! history token))
