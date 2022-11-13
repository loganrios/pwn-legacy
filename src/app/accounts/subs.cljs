(ns app.accounts.subs
  (:require [re-frame.core :refer [reg-sub]]
            [app.db :refer [<sub]]
            [app.misc :refer [author-works
                              works-list-item
                              id->work
                              author-works-by-status
                              word-count]]
            [app.db :as db]))

(reg-sub
 :user
 (fn [db [_]]
   (get-in db [:user/id])))

(reg-sub
 :username
   (fn [db [_ id]]
     (get-in db [:users id :user/username])))

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

(reg-sub
 :author/works-by-status
 (fn [db [_ author-id status]]
   (map #(works-list-item %)
        (map #(id->work db %)
             (author-works-by-status db author-id status)))))

(comment

  (word-count app.db/dev-db :TheBeginning)

  (filter #(= :Taz (:work/owner %)) (vals (:works app.db/dev-db)))

  (author-works app.db/dev-db :Taz)

  (id->work app.db/dev-db 2)

  (<sub [:author/works-by-status :Taz :completed])

  nil)
