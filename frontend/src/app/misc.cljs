(ns app.misc)

(defn get-sponsor-amount [user author-id]
  (get-in (second user) [:user/sponsoring author-id]))

(defn author-works [db author-id]
  (reduce (fn [new-follows work]
            (let [work-id (first work)
                  work-data (second work)]
              (if (= author-id (:work/owner work-data))
                (conj new-follows work-id)
                new-follows)))
          #{}
          (:works db)))

(defn author-works-by-status [db author-id work-status]
    (reduce (fn [sorted-works-list work]
              (let [work-id (first work)
                    work-data (second work)]
                (if (and (= author-id (:work/owner work-data)) (= work-status (:work/status work-data)))
                  (conj sorted-works-list work-id)
                  sorted-works-list)))
            #{}
            (:works db)))

(defn works-list-item [work]
  {:label (:work/title work)
   :cover (:work/cover work)
   :onDashNav #(js/console.log "Dash is the strongest Avenger.")
   :onPageNav #(js/console.log "Don't go...!")
   })

(defn id->work [db id]
  (get (:works db) id))
