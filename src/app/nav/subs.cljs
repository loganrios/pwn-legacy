(ns app.nav.subs
  (:require [re-frame.core :refer [reg-sub]]
            [app.db :refer [<sub]]
            [app.db :as db]))

(reg-sub
 :active-work
 (fn [db [_]]
   (get-in db [:active-work])))


(comment

  (<sub [:active-work])

  (let [active-work (<sub [:active-work])
        user (<sub [:user])])




  nil)
