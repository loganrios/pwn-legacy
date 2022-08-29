(ns app.accounts.subs
  (:require [re-frame.core :refer [reg-sub]]
            [app.db :refer [<sub]]))

 (reg-sub :user
           (fn [db [_ uuid]]
             (get-in db [uuid])))


(comment


 (reg-sub :user-info
           (fn [db [_ uuid]]
             (get-in db [uuid])))

 (get-in app.db/dev-db [:Taz :user/username])

(<sub [:user :Devrey])

 nil)
