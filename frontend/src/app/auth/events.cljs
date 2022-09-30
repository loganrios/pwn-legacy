(ns app.auth.events
  (:require [re-frame.core :refer [reg-event-db
                                   reg-event-fx]]
            [app.db :refer [<sub
                            >evt]]
            [app.events]
            [day8.re-frame.http-fx]
            [ajax.core :refer [json-request-format
                               json-response-format]]
            [clojure.string :as str]

            [app.misc :refer [endpoint]]))

(reg-event-fx
 :auth/login
 (fn [{:keys [db]} [evt-nm email password]]
   {:http-xhrio {:method :post
                 :uri (endpoint "users" "auth-via-email")
                 :params {:email email :password password}
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-success [:auth/login-success]
                 :on-failure [:request-error evt-nm]}}))

(reg-event-db
 :auth/login-success
 (fn [db [_ {:keys [token user]}]]
   (let [{:keys [id email]} user]
     (-> db
      (assoc :user/id id)
      (assoc :user/email email)
      (assoc :token token)))))

(reg-event-db
 :auth/logout
 (fn [db _]
   (-> db
       (assoc :user/id nil)
       (assoc :user/email nil)
       (assoc :token nil))))

(comment


  (>evt [:auth/login "dummyemail@realauthor.com" "password"])

  (>evt [:auth/login "thisemail@email.comm" "passwordd"])

  (>evt [:auth/logout])

  (<sub [:db])


 nil)
