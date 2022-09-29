(ns app.misc
  (:require [clojure.string :as str]))

(def api-url "https://projectwebnovel.com/api")

(defn endpoint [& params]
  (str/join "/" (cons api-url params)))

(defn vec->idxent
  [v]
  (reduce #(assoc %1 (:chapter/id %2) %2) {} v))

(defn map->nsmap
  [m n]
  (reduce-kv (fn [acc k v]
              (let [new-kw (if (and (keyword? k)
                                    (not (qualified-keyword? k)))
                             (keyword (str n) (name k))
                             k)]
                (assoc acc new-kw v)))
            {} m))

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
   :onAddChapter #(js/console.log "Add a New Chaptered")})
   

(defn id->work [db id]
  (get (:works db) id))

(defn word-count [db chapter-id]
  (count (str/split (get-in db [:chapters chapter-id :chapter/content]) #"\s+")))

(defn chapter-views [db chapter-id]
  (get-in db [:chapters chapter-id :chapter/hits]))
