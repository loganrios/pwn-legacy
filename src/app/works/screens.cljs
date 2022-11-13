(ns app.works.screens
  (:require  ["/DashboardChapterList$default" :as DashboardChapterList]
             ["/WorkDashboardInfo$default" :as DashboardInfo]
             ["/WorkScreenInfo$default" :as WorkInfo]
             ["/WorkChapterList$default" :as WorkChapterList]
             [app.db :refer [<sub
                             >evt]]
             [app.router :refer [url-for]]
             [app.works.events]
             [app.works.subs]
             [app.nav.events]
             [app.nav.subs]
             [reagent.core :as r]
             ["@mui/material" :as mui]))

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
  (let [active-work (<sub [:active-work])
        work-info (<sub [:works active-work])]
    [:<>
     [:> WorkInfo {:workTitle (:work/title work-info)
                   :author (<sub [:username (:work/owner work-info)])
                   :blurb (:work/blurb work-info)
                   :image (:work/cover work-info)
                   :avgPostTime "7 days"
                   :avgChapterLength "2500 words"
                   :totalViews 49866
                   :averageViews 42
                   :followers 27
                   :publicRatings 12
                   :pages 8845
                   :genres (<sub [:works/genres active-work])
                   :tags (<sub [:works/tags active-work])
                   :warnings (<sub [:works/warnings active-work])}]
     [:> WorkChapterList {:chapters (<sub [:works/work-chapter-list active-work])}]
     [:> mui/Button {:variant "contained"
                     :href (url-for :work :slug 2)}]]))
