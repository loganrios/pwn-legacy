(ns app.accounts.events
  (:require [re-frame.core :refer [reg-event-db]]
            [app.db]))

(reg-event-db
 :accounts/login
 (fn [db [_ username password]]
   (-> db
       (assoc :user/email username)
       (assoc :jwt "some random string"))))
