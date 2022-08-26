(ns app.index
 (:require [reagent.core :as r]
           [reagent.dom :as rdom]
           [re-frame.core :as rf]

           ["react" :as react]
           ["@mui/material" :as mui]
           ["@mui/icons-material" :as mui-icons]

           ["/Login$default" :as Login]))

(defn main []
 [:meta {:name "viewport" :content "initial-scale=1, width=device-width"}]
 [:> Login {:onLoginSubmit #(js/console.log "Logging in!")
            :onRegisterNav #(js/console.log "Navigating to Registration...")}])

(defn start []
  (rdom/render [main] (js/document.getElementById "app")))

(start)
