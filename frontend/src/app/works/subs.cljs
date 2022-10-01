(ns app.works.subs
  (:require [re-frame.core :refer [reg-sub]]
            [app.db :refer [<sub
                            >evt]]
            [app.misc :refer [workId->chapter-list
                              dashboard-chapter-list-item]]
            [app.db :as db]))

(reg-sub
 :works
 (fn [db [_ work-id]]
   (get-in db [:works work-id])))

(reg-sub
 :works/dashboard-chapter-list
 (fn [db [_ work-id]]
   (let [chapter-list (vals (first (workId->chapter-list db work-id)))]
     (map #(dashboard-chapter-list-item db %) chapter-list))))

(comment


 (<sub [:works/work-info 1])


 (workId->chapter-list app.db/dev-db 1)

 (>evt [:initialize-db])

 (<sub [:db])

 (<sub [:works/dashboard-chapter-list 1])



 nil)
