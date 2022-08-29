(ns app.accounts.screens
  (:require  ["/Login$default" :as Login]
             ["/AccountRegistration$default" :as Register]
             ["/AuthorBio$default" :as Bio]
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
    [:> Bio {:isOwner false
             :username (get user :user/username)
             :statsText "These are stats"
             :bioText (get user :user/bioText)
             :image (get user :user/image)
             :onFollow #(js/console.log "STALKER!")
             :onSponsor #(js/console.log "Thanks for falling for my scam!")}]))
