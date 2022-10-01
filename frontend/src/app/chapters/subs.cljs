(ns app.chapters.subs
  (:require [re-frame.core :refer [reg-sub]]
            [app.db :refer [<sub
                            >evt]]
            [app.misc]
            [app.db :as db]))

(reg-sub
 :chapters
 (fn [db [_ chapter-id]]
    (get-in db [:chapters chapter-id])))


(comment

  nil)
