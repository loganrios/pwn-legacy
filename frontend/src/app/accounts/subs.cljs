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

(defn get-sponsor-amount [user author-id]
 (get-in (second user) [:user/sponsors author-id]))

(reg-sub
 :author/sponsor-data
 (fn [db [_ author-id]]
    (reduce (fn [sponsors user]
              (if (get-sponsor-amount user author-id)
                  (conj sponsors {:id (first user)
                                  :username (:user/username (second user))
                                  :donation (get-sponsor-amount user author-id)})
                  sponsors))
            []
            (:users db))))
(comment
 nil)
