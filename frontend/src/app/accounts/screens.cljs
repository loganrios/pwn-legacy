(ns app.accounts.screens
  (:require  ["/Login$default" :as Login]
             ["/AccountRegistration$default" :as Register]
             ["/AuthorBio$default" :as Bio]
             ["/SponsorList$default" :as SponsorList]
             [app.db :refer [<sub
                             >evt]]
             [app.accounts.events]
             [app.accounts.subs]))

(defn LoginScreen []
  [:> Login {:onLoginSubmit #(>evt [:accounts/login %1 %2])
             :onRegisterNav #(js/console.log "Navigating to Registration...")}])

(defn RegisterScreen []
  [:> Register {:onRegisterSubmit #(>evt [:accounts/register %1 %2 %3])
                :onLoginNav #(js/console.log "Don't go! I promise I'll be better...")}])

(defn AuthorProfileScreen []
  (let [user (<sub [:user :Taz])]
    [:<>
     [:> Bio {:isOwner false
              :username (get user :user/username)
              :wordCount 51432
              :reviewsCount 69
              :ratingsCount 420
              :bio (get user :user/bio)
              :image (get user :user/image)
              :readingList (get user :user/readingList)
              :link (<sub [:links :Taz])
              :desc "Please enter the amount you are paying towards your sponsorship of this author."
              :onFollow #(js/console.log "STALKER!")
              :onSubmit #(js/console.log "Thanks for falling for my scam!")
              :onEditAvatar #(js/console.log "Why change a perfectly good picture?")}]
     [:> SponsorList {:sponsors (<sub [:author/sponsor-data :Taz])
                                 }]]))
