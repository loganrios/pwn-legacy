(ns app.accounts.subs
  (:require [re-frame.core :refer [reg-sub]]
            [app.db :refer [<sub]]
            [app.misc :as misc]
            [app.db :as db]))

(reg-sub
 :user
 (fn [db [_ uuid]]
   (get-in db [:users uuid])))

(<sub [:user :Taz])

(reg-sub
 :author/format-links
 (fn [db [_ uuid]]
   (map (fn [field]
          (let [name (first field)]
            (assoc {}
                   :label name
                   :url (second field))))
        (get-in db [:users uuid :user/links]))))

(reg-sub
 :author/sponsor-data
 (fn [db [_ author-id]]
   (reduce (fn [sponsors user]
             (let [user-id (first user)
                   user-data (second user)
                   sponsor-amount (get-in user-data [:user/sponsoring author-id])]
               (if sponsor-amount
                 (conj sponsors {:id user-id
                                 :username (:user/username user-data)
                                 :donation sponsor-amount})
                 sponsors)))
           []
           (:users db))))

(reg-sub :author/works-by-status
         (fn [db [_ author-id status]]
           (map #(misc/works-list-item %)
                (map #(misc/id->work db %)
                     (misc/author-works-by-status db author-id status)))))



(comment

  (<sub [:chapter/word-count :TheBeginning])

  (filter #(= :Taz (:work/owner %)) (vals (:works app.db/dev-db)))

  (misc/author-works app.db/dev-db :Taz)

  (misc/id->work app.db/dev-db 2)

  (<sub [:author/works-by-status :Taz :completed])

  nil)
