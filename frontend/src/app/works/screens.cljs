(ns app.works.screens
   (:require  ["/DashboardChapterList$default" :as DashboardChapterList]
              ["/WorkDashboardInfo$default" :as WorkInfo]
              [app.db :refer [<sub
                              >evt]]
              [app.works.events]
              [app.works.subs]
              [reagent.core :as r]))

(defn WorkDashboardScreen []
  (let [work-id 1
        work (<sub [:works work-id])]
    [:<>
     [:> WorkInfo {:workTitle (:work/title work)
                   :blurb (:work/blurb work)
                   :image (:work/cover work)
                   :onEdit #(js/console.log "Edittt!")
                   :onGoToPage #(js/console.log "Don't go! I need you!")
                   :onEditCover #(js/console.log "Don't fix what ain't broke.")}]
     [:> DashboardChapterList {:chapters (<sub [:works/dashboard-chapter-list 1])}]]))
