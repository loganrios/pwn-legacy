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
 (fn [{:keys [db]} [evt-nm username email password passwordConfirm]]
   {:http-xhrio {:method :post
                 :uri (endpoint "users")
                 :params {:profile-username username
                          :email email
                          :password password
                          :passwordConfirm passwordConfirm
                          :profile-privilege "reader"
                          :profile-reader_preferences {":adult-content" "false"
                                                       ":track-progress" "true"}}
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-failure [:request-error evt-nm]}}))


;; (reg-event-fx
;;  :work/create
;;  (fn [{:keys [db]} [evt-nm title owner visibility hits status]]
;;    {:http-xhrio {:method :post
;;                  :uri (endpoint "collections" "works" "records")
;;                  :params {:title title :owner owner :visibility visibility :hits hits :status status}
;;                  :format (json-request-format)
;;                  :response-format (json-response-format {:keywords? true})
;;                  :on-failure [:request-error evt-nm]}}))


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

   (reg-event-fx
    :users/get
    (fn [{:keys [db]} [evt-nm]]
      {:http-xhrio {:method :get
                    :uri (endpoint "users")
                    :format (json-request-format)
                    :response-format (json-response-format {:keywords? true})
                    :on-success [:users/get-success]
                    :on-failure [:request-error evt-nm]}}))

   (reg-event-db
    :users/get-success
    (fn [db [_ {:keys [items]}]]
      items))



      ;; (-> db
      ;;  (assoc :users (vec->idxent (map (fn [item] (map->nsmap item "user")) items) :user/id)))))


   (>evt [:users/get])

   (>evt [:user/create "Fake User" "thisemail@email.comm" "passwordd" "passwordd"])

   (>evt [:follow-all-author-works :Taz :Taz])

   (<sub [:db])

   (>evt [:initialize-db])


   nil)
