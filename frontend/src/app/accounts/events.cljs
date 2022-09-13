(ns app.accounts.events
  (:require [re-frame.core :refer [reg-event-db]]
            [app.db :refer [<sub
                            >evt]]
            [app.misc :as misc]))

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

(reg-event-db
 :edit-url
 (fn [db [_ user-id label url]]
   (assoc-in db [:users user-id :user/links label] url)))


(reg-event-db
 :follow-all-author-works
 (fn [db [_ user-id author-id]]
   (assoc-in db [:users user-id :user/following]
            (set (concat (get-in db [:users user-id :user/following])
                     (misc/author-works db author-id))))))

(comment

  (let [db app.db/dev-db
        author-id :Taz
        user-id :Leif]
    (assoc-in db [:users user-id :user/following]
              (set (concat (get-in db [:users user-id :user/following])
                      (reduce (fn [new-follows work]
                                (let [work-id (first work)
                                      work-data (second work)]
                                  (if (= author-id (:work/owner work-data))
                                    (conj new-follows work-id)
                                    new-follows)))
                              []
                              (:works db))))))

  (>evt [:follow-all-author-works :Devrey :Taz])

  (<sub [:db])

  (defn author-works [db author-id]
    (reduce (fn [new-follows work]
              (let [work-id (first work)
                    work-data (second work)]
                (if (= author-id (:work/owner work-data))
                  (conj new-follows work-id)
                  new-follows)))
            []
            (:works db)))

  (author-works app.db/dev-db :Taz)

  (<sub [:db])

  nil)
