(ns app.accounts.events
  (:require [re-frame.core :refer [reg-event-db]]
            [app.db :refer [<sub
                            >evt]]))

(reg-event-db
 :accounts/login
 (fn [db [_ email password]]
   (-> db
       (assoc :user/email email)
       (assoc :jwt "some random string"))))

(reg-event-db
 :accounts/register
 (fn [db [_ username email password]]
   (-> db
       (assoc :user/name username)
       (assoc :user/email email)
       (assoc :jwt "some random string"))))

;; (reg-event-db
;;  :user/edit-links
;;  (fn [db [_ uuid]]))


(comment
(reg-event-db
 :edit-url
 (fn [db [_ user-id label url]]
   (assoc-in db [:users user-id :user/links label] url)))

(>evt [:edit-url :Taz "Discord" "fakediscord.smurf"])

 nil)
