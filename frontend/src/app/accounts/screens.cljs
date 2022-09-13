(ns app.accounts.screens
  (:require  ["/Login$default" :as Login]
             ["/AccountRegistration$default" :as Register]
             ["/AuthorBio$default" :as Bio]
             ["/SponsorList$default" :as SponsorList]
             ["/WorksList$default" :as Works]
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
  (let [user-id :Devrey
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

(comment

  (let [selected-status-tab (r/atom 0)]
    (reset! selected-status-tab 2))

  nil)
