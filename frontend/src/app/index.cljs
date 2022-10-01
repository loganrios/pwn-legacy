(ns app.index
 (:require [reagent.core :as r]
           [reagent.dom :as rdom]
           [re-frame.core :as rf]

           ["react" :as react]
           ["@mui/material" :as mui]
           ["@mui/icons-material" :as mui-icons]

           [app.db]
           [app.accounts.screens :as account-screens]
           [app.works.screens :as work-screens]
           [app.chapters.screens :as chapter-screens]))

(defn rfy [screen]
  (r/reactify-component screen))

(defn main []
 [:meta {:name "viewport" :content "initial-scale=1, width=device-width"}]
 ;; [:> (rfy account-screens/LoginScreen)]
 ;; [:> (rfy account-screens/RegisterScreen)]
 ;; [:> (rfy account-screens/AuthorProfileScreen)]
 ;; [:> (rfy account-screens/AuthorDashboardScreen)])
 [:> (rfy work-screens/WorkDashboardScreen)])

(defn start []
  (rdom/render [main] (js/document.getElementById "app")))

(rf/dispatch-sync [:initialize-db])


(start)
