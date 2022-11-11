(ns app.works.screens
   (:require  ["/DashboardChapterList$default" :as DashboardChapterList]
              ["/WorkDashboardInfo$default" :as DashboardInfo]
              ["/WorkScreenInfo$default" :as WorkInfo]
              ["/WorkChapterList$default" :as WorkChapterList]
              [app.db :refer [<sub
                              >evt]]
              [app.works.events]
              [app.works.subs]
              [reagent.core :as r]))

(defn WorkDashboardScreen []
  (let [work-id 1
        work (<sub [:works work-id])]
    [:<>
     [:> DashboardInfo {:workTitle (:work/title work)
                        :blurb (:work/blurb work)
                        :image (:work/cover work)
                        :onEdit #(js/console.log "Edittt!")
                        :onGoToPage #(js/console.log "Don't go! I need you!")
                        :onEditCover #(js/console.log "Don't fix what ain't broke.")}]
     [:> DashboardChapterList {:chapters (<sub [:works/dashboard-chapter-list work-id])}]]))

(defn WorkScreen []
  (let [work-id 1
        work (<sub [:works work-id])]
    [:<>
     [:> WorkInfo {:workTitle (:work/title work)
                   :author (<sub [:username (:work/owner work)])
                   :blurb (:work/blurb work)
                   :image (:work/cover work)
                   :avgPostTime "7 days"
                   :avgChapterLength "2500 words"
                   :totalViews 49866
                   :averageViews 42
                   :followers 27
                   :publicRatings 12
                   :pages 8845
                   :genres (<sub [:works/genres work-id])
                   :tags (<sub [:works/tags work-id])
                   :warnings (<sub [:works/warnings work-id])}]
     [:> WorkChapterList {:chapters (<sub [:works/work-chapter-list work-id])}]]))
