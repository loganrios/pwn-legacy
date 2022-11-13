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
                              userId->profileId
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
 (fn [_ [evt-nm username email password passwordConfirm]]
   {:http-xhrio {:method :post
                 :uri (endpoint "users")
                 :params {:email email
                          :password password
                          :passwordConfirm passwordConfirm}
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-success [:user/create-profile username]
                 :on-failure [:request-error evt-nm]}}))

(reg-event-fx
 :user/create-profile
 (fn [_ [evt-nm username {:keys [profile]}]]
   {:http-xhrio {:method :patch
                 :uri (endpoint "collections" "profiles" "records" (:id profile))
                 :params {:username username :privilege "reader"}
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-failure [:request-error evt-nm]}}))
(reg-event-fx
 :profiles/get
 (fn [_ [evt-nm]]
   {:http-xhrio {:method :get
                 :uri (endpoint "collections" "profiles" "records")
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-success [:profiles/get-success]
                 :on-failure [:request-error evt-nm]}}))

(reg-event-db
 :profiles/get-success
 (fn [db [_ {:keys [items]}]]
   (-> db
    (assoc :users (vec->idxent (map (fn [item] (map->nsmap item "user")) items) :user/userId)))))

(reg-event-fx
 :profile/get
 (fn [_ [evt-nm id]]
   {:http-xhrio {:method :get
                 :uri (endpoint "collections" "profiles" "records" id)
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-success [:profile/get-success]
                 :on-failure [:request-error evt-nm]}}))

(reg-event-db
 :profile/get-success
 (fn [db [_ %]]
   (let [user (map->nsmap % "user")]
     (-> db
      (assoc :users (assoc {} (:user/userId user) user))))))

(reg-event-fx
 :profile/update
 (fn [_ [evt-nm id field new-val]]
   {:http-xhrio {:method :patch
                 :uri (endpoint "collections" "profiles" "records" id)
                 :params {field new-val}
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-failure [:request-error evt-nm]}}))

;;TODO this may not be necessary. Discuss with Logan
(reg-event-fx
 :profile/delete
 (fn [_ [evt-nm id]]
   {:http-xhrio {:method :delete
                 :uri (endpoint "collections" "profiles" "records" id)
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



  (userId->profileId app.db/dev-db :Taz)

  (>evt [:profiles/get])

  (>evt [:profile/get (userId->profileId "hz5p7g21fca6k2w")])

  (>evt [:profile/get "627yqun0gnk0cky"])

  (>evt [:profile/delete "hibkbmrop7gidxv"])

  (>evt [:profile/update "1nkqyfnr7dztkqc" :username "FakeUser" :privilege "reader"])

  (>evt [:user/create "Tazspeare" "Tazemail@email.com" "passwordd" "passwordd"])

  (>evt [:follow-all-author-works :Taz :Taz])

  (<sub [:db])

  (>evt [:initialize-db])


 nil)
