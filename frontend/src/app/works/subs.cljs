(ns app.works.subs
  (:require [re-frame.core :refer [reg-sub]]
            [app.db :refer [<sub
                            >evt]]
            [app.misc :refer [workId->chapter
                              dashboard-chapter-list-item]]
            [app.db :as db]))

(reg-sub
 :works/dashboard-chapter-list
 (fn [db [_ work-id]]
   (let [chapter-list (vals (first (workId->chapter db work-id)))]
     (map #(dashboard-chapter-list-item db %) chapter-list))))


(comment


  (workId->chapter app.db/dev-db 1)

  (>evt [:initialize-db])

  (<sub [:db])

  (<sub [:works/dashboard-chapter-list 1])



 nil)
