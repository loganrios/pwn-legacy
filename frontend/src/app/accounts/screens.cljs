(ns app.accounts.screens
  (:require  ["/Login$default" :as Login]
             ["/AccountRegistration$default" :as Register]
             ["/AuthorBio$default" :as Bio]
             ["/SponsorList$default" :as SponsorList]
             ["/WorksList$default" :as Works]
             ["/DashboardWorksList$default" :as DashboardWorks]
             ["/DashboardChapterList$default" :as DashboardChapterList]
             [app.db :refer [<sub
                             >evt]]
             [app.accounts.events]
             [app.accounts.subs]
             [reagent.core :as r]))

(defn LoginScreen []
  [:> Login {:onLoginSubmit #(>evt [:accounts/login %1 %2])
             :onRegisterNav #(js/console.log "Navigating to Registration...")}])

(defn RegisterScreen []
  [:> Register {:onRegisterSubmit #(>evt [:accounts/register %1 %2 %3])
                :onLoginNav #(js/console.log "Don't go! I promise I'll be better...")}])

(defn AuthorProfileScreen []
  (let [user-id :Taz
        user (<sub [:user user-id])
        author-id :Taz]
    [:<>
     [:> Bio {:isOwner false
              :username (get user :user/username)
              :wordCount 51432
              :reviewsCount 69
              :ratingsCount 420
              :bio (get user :user/bio)
              :image (get user :user/image)
              :readingList (get user :user/readingList)
              :link (<sub [:author/format-links user-id])
              :desc "Please enter the amount you are paying towards your sponsorship of this author."
              :onFollow #(>evt [:follow-all-author-works user-id author-id])
              :onSubmit #(js/console.log "Thanks for falling for my scam!")
              :onEditAvatar #(js/console.log "Why change a perfectly good picture?")}]
     [:> SponsorList {:sponsors (<sub [:author/sponsor-data user-id])}]
     [:> Works {:viewTab 0
                :ongoing (<sub [:author/works-by-status user-id :ongoing])
                :completed (<sub [:author/works-by-status user-id :completed])
                :hiatus (<sub [:author/works-by-status user-id :hiatus])}]]))

(defn AuthorDashboardScreen []
  (let [user-id :Taz]
    [:<>
     [:> DashboardWorks {:viewTab 0
                         :ongoing (<sub [:author/works-by-status user-id :ongoing])
                         :completed (<sub [:author/works-by-status user-id :completed])
                         :hiatus (<sub [:author/works-by-status user-id :hiatus])}]]))

(defn WorkDashboardScreen []
  (let [user-id :Taz
        user (<sub [:user user-id])
        author-id :Taz]
    [:<>
     [:> DashboardChapterList {:chapters [{:id 1
                                           :title "Of Stocks and Demons"
                                           :date "9/4/2022, 12:00 PM"
                                           :words 472
                                           :views 60
                                           :comment "Taz"
                                           :onEdit #(js/console.log "Edited")
                                           :onDelete #(js/console.log "Deleted")}
                                          {:id 2
                                           :title "Of Stocks and Demons"
                                           :date "9/4/2022, 12:00 PM"
                                           :words 472
                                           :views 60
                                           :comment "Taz"
                                           :onEdit #(js/console.log "Edited")
                                           :onDelete #(js/console.log "Deleted")}]}]]))

(comment

  nil)
