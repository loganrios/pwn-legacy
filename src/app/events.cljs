(ns app.events
  (:require [re-frame.core :refer [reg-event-db
                                   reg-event-fx]]
            [day8.re-frame.http-fx]))

(reg-event-db
 :request-error
 (fn [db [_ request-name]]
   (js/console.log (str "Request Failed: " request-name))))


