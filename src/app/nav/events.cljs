(ns app.nav.events
  (:require [re-frame.core :refer [reg-event-db
                                   reg-event-fx]]
            [day8.re-frame.http-fx]

            [app.works.events]
            [app.accounts.events]
            [app.misc :refer [userId->profileId]]))

(reg-event-fx
 :set-active-page
 (fn [{:keys [db]} [_ {:keys [page slug]}]]
   (let [set-page (assoc db :active-page page)]
     (case page
       (:login :register) {:db set-page}
       :work {:db (assoc set-page :active-work slug)
              :dispatch-n [[:works/get]
                           [:profile/get (userId->profileId db (get-in db [:works slug :owner]))]]}))))
