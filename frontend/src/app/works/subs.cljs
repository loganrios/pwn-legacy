(ns app.works.subs
  (:require [re-frame.core :refer [reg-sub]]
            [app.db :refer [<sub
                            >evt]]
            [app.misc :refer [workId->chapter-list
                              dashboard-chapter-list-item
                              work-chapter-list-item]]
            [app.db :as db]))

(reg-sub
 :works
 (fn [db [_ work-id]]
   (get-in db [:works work-id])))

(reg-sub
 :works/dashboard-chapter-list
 (fn [db [_ work-id]]
   (let [chapter-list (vals (first (workId->chapter-list db work-id)))]
     (map #(dashboard-chapter-list-item db %) chapter-list))))

(reg-sub
 :works/work-chapter-list
 (fn [db [_ work-id]]
   (let [chapter-list (vals (first (workId->chapter-list db work-id)))]
     (map #(work-chapter-list-item db %) chapter-list))))

;;FIXME tag-filters is hacked until nav is implemented
(reg-sub
 :works/genres
 (fn [db [_ work-id]]
   (let [type (get-in db [:works work-id :work/genres])
         tag-filters [#(js/console.log "Filter to...") #(js/console.log "Filter to...")]]
     (map #(assoc {} :label %1 :onTagFilter %2) type tag-filters))))

;;FIXME tag-filters is hacked until nav is implemented
(reg-sub
 :works/tags
 (fn [db [_ work-id]]
   (let [type (get-in db [:works work-id :work/tags])
         tag-filters [#(js/console.log "Filter to...") #(js/console.log "Filter to...")
                      #(js/console.log "Filter to...") #(js/console.log "Filter to...")
                      #(js/console.log "Filter to...")]]
     (map #(assoc {} :label %1 :onTagFilter %2) type tag-filters))))

;;FIXME tag-filters is hacked until nav is implemented
(reg-sub
 :works/warnings
 (fn [db [_ work-id]]
   (let [type (get-in db [:works work-id :work/warnings])
         tag-filters [#(js/console.log "Filter to...") #(js/console.log "Filter to...")]]
     (map #(assoc {} :label %1 :onTagFilter %2) type tag-filters))))

(comment


 (<sub [:works/genres 1])

 (let [genres ["Fantasy" "Romance"]])


 (defn other-placeholder-nav-function
   [genre]
   #(js/console.log (str genre)))

 (<sub [:works/work-chapter-list 1])

 (<sub [:works/work-info 1])

 (workId->chapter-list app.db/dev-db 1)

 (>evt [:initialize-db])

 (<sub [:db])

 (<sub [:works/dashboard-chapter-list 1])



 nil)
