(ns app.chapters.events
  (:require [re-frame.core :refer [reg-event-db
                                   reg-event-fx]]
            [app.db :refer [<sub
                            >evt]]
            [app.events]
            [day8.re-frame.http-fx]
            [ajax.core :refer [json-request-format
                               json-response-format]]

            [app.misc :refer [vec->idxent
                              map->nsmap
                              endpoint]]))

(reg-event-fx
 :chapters/get
 (fn [{:keys [db]} [evt-nm]]
   {:http-xhrio {:method :get
                 :uri (endpoint "collections" "chapters" "records")
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-success [:chapters/get-success]
                 :on-failure [:request-error evt-nm]}}))

(reg-event-db
 :chapters/get-success
 (fn [db [_ {:keys [items]}]]
   (-> db
    (assoc :works (vec->idxent (map (fn [item] (map->nsmap item "chapter")) items))))))
