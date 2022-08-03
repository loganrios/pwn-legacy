(ns app.core
 (:require [reagent.core :as r]
           [reagent.dom :as rdom]

           ["react" :as react]
           ["@mui/material" :as mui]
           ["@mui/icons-material" :as mui-icons]

           ["../stories/Button" :as Button]))

(defn main []
 [:meta {:name "viewport" :content "initial-scale=1, width=device-width"}]
 [:> mui/Button {:variant "contained"} "boof pack"])

(defn start []
  (rdom/render [main] (js/document.getElementById "app")))

(start)
