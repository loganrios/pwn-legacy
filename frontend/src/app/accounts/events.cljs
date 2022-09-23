(ns app.accounts.events
  (:require [re-frame.core :refer [reg-event-db]]
            [app.db :refer [<sub
                            >evt]]
            [app.misc :as misc]))

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

(reg-event-db
 :edit-url
 (fn [db [_ user-id label url]]
   (assoc-in db [:users user-id :user/links label] url)))

(reg-event-db
 :follow-all-author-works
 (fn [db [_ user-id author-id]]
   (assoc-in db [:users user-id :user/following]
            (set (concat (get-in db [:users user-id :user/following])
                     (misc/author-works db author-id))))))

(comment


  (>evt [:follow-all-author-works :Taz :Taz])


  (<sub [:db])




  nil)
