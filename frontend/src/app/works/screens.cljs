(ns app.works.screens
   (:require  ["/DashboardChapterList$default" :as DashboardChapterList]
             [app.db :refer [<sub
                             >evt]]
             [app.works.events]
             [app.works.subs]
             [reagent.core :as r]))

(defn WorkDashboardScreen []
  (let [user-id :Taz
        user (<sub [:user user-id])
        author-id :Taz]
    [:<>
     [:> DashboardChapterList {:chapters (<sub [:works/dashboard-chapter-list 1])}]]))
