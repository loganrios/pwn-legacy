(ns app.accounts.subs
  (:require [re-frame.core :refer [reg-sub]]
            [app.db :refer [<sub]]))

(reg-sub :user
         (fn [db [_ uuid]]
           (get-in db [:users uuid])))

(reg-sub :links
           (fn [db [_ uuid]]
             (map (fn [field]
                    (let [name (first field)]
                      (assoc {}
                             :label name
                             :url (second field))))
                  (get-in db [:users uuid :user/links]))))

(comment

;; (:label (:name [:Taz :links)
;;  :url (:value ))

  (get-in app.db/dev-db [:users :Taz])

  (<sub [:user :Taz])

  nil)
