(ns app.accounts.events
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
                              endpoint
                              author-works]]))
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


(reg-event-fx
 :user/create
 (fn [{:keys [db]} [evt-nm email password passwordConfirm]]
   {:http-xhrio {:method :post
                 :uri (endpoint "collections" "works" "records")
                 :params {:email email :password password :passwordConfirm passwordConfirm}
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-failure [:request-error evt-nm]}}))




(reg-event-db
 :edit-url
 (fn [db [_ user-id label url]]
   (assoc-in db [:users user-id :user/links label] url)))

(reg-event-db
 :follow-all-author-works
 (fn [db [_ user-id author-id]]
   (assoc-in db [:users user-id :user/following]
            (set (concat (get-in db [:users user-id :user/following])
                     (author-works db author-id))))))

(comment


  (>evt [:follow-all-author-works :Taz :Taz])


  (<sub [:db])




  nil)
