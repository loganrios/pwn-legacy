(ns app.index
 (:require [reagent.core :as r]
           [reagent.dom :as rdom]
           [re-frame.core :as rf]

           ["react" :as react]
           ["@mui/material" :as mui]
           ["@mui/icons-material" :as mui-icons]))


(defn main []
 [:meta {:name "viewport" :content "initial-scale=1, width=device-width"}]
 [:div
  [:> mui/Button {:onClick #(js/console.log "Logged in!")
                  :variant "contained"}
   "Log in!"]])

(defn start []
  (rf/dispatch-sync [:initialize])
  (rdom/render [main] (js/document.getElementById "app")))

(start)
