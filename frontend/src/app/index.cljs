(ns app.index
 (:require [reagent.core :as r]
           [reagent.dom :as rdom]
           [re-frame.core :as rf]

           ["react" :as react]
           ["@mui/material" :as mui]
           ["@mui/icons-material" :as mui-icons]

           [app.db]
           [app.accounts.screens :as account-screens]))

(defn rfy [screen]
  (r/reactify-component screen))

(defn main []
 [:meta {:name "viewport" :content "initial-scale=1, width=device-width"}]
 ;; [:> (rfy account-screens/LoginScreen)]
 ;; [:> (rfy account-screens/RegisterScreen)]
   [:> (rfy account-screens/AuthorProfileScreen)])
(defn start []
  (rdom/render [main] (js/document.getElementById "app")))

(rf/dispatch-sync [:initialize-db])


(start)
