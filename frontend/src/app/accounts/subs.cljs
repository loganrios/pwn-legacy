(ns app.accounts.subs
  (:require [re-frame.core :refer [reg-sub]]
            [app.db :refer [<sub]]
            [app.misc :as misc]
            [app.db :as db]))

(reg-sub
 :user
 (fn [db [_ uuid]]
   (get-in db [:users uuid])))

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

  (filter #(= :Taz (:work/owner %)) (vals (:works db/dev-db)))

  (misc/author-works app.db/dev-db :Taz)

  (misc/id->work app.db/dev-db 2)


  (<sub [:author/works-by-status :Taz :completed])
  ;; => ({:value #object[cljs$core$rand_int],
  ;; :label "World of Broken Dreams",
  ;; :cover "https://www.royalroadcdn.com/public/covers-large/world-of-broken-dreams-63504.jpg?time=1661125629",
  ;; :onDashNav #object[Function],
  ;; :onPageNav #object[Function]}
  ;; {:value #object[cljs$core$rand_int],
  ;; :label "Black Reflections",
  ;; :cover "https://www.royalroadcdn.com/public/covers-large/world-of-broken-dreams-63504.jpg?time=1661125629",
  ;; :onDashNav #object[Function],
  ;; :onPageNav #object[Function]})


  nil)
