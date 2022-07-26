(ns app.chapters.events
  (:require [re-frame.core :refer [reg-event-db
                                   reg-event-fx]]
            [app.db :refer [<sub
                            >evt]]
            [app.events]
            [day8.re-frame.http-fx]
            [ajax.core :refer [json-request-format
                               json-response-format]]

            [app.misc :refer [vec->idxent
                              map->nsmap
                              endpoint]]))

(reg-event-fx
 :chapters/get
 (fn [{:keys [db]} [evt-nm]]
   {:http-xhrio {:method :get
                 :uri (endpoint "collections" "chapters" "records")
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-success [:chapters/get-success]
                 :on-failure [:request-error evt-nm]}}))

(reg-event-db
 :chapters/get-success
 (fn [db [_ {:keys [items]}]]
   (-> db
    (assoc :chapters (vec->idxent (map (fn [item] (map->nsmap item "chapter")) items) :chapter/id)))))

(reg-event-fx
 :chapter/get
 (fn [{:keys [db]} [evt-nm id]]
   {:http-xhrio {:method :get
                 :uri (endpoint "collections" "chapters" "records" id)
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-success [:chapter/get-success]
                 :on-failure [:request-error evt-nm]}}))

(reg-event-db
 :chapter/get-success
 (fn [db [_ %]]
   (let [chapter (map->nsmap % "chapter")]
     (-> db
      (assoc :chapters (assoc {} (:chapter/id chapter) chapter))))))

(reg-event-fx
 :chapter/create
 (fn [{:keys [db]} [evt-nm title content authors]]
   {:http-xhrio {:method :post
                 :uri (endpoint "collections" "chapters" "records")
                 :params {:title title :content content :authors authors :hits 0}
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-failure [:request-error evt-nm]}}))
(reg-event-fx
 :chapter/update
 (fn [{:keys [db]} [evt-nm id field new-val]]
   {:http-xhrio {:method :patch
                 :uri (endpoint "collections" "chapters" "records" id)
                 :params {field new-val}
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-failure [:request-error evt-nm]}}))

(reg-event-fx
 :chapter/delete
 (fn [{:keys [db]} [evt-nm id]]
   {:http-xhrio {:method :delete
                 :uri (endpoint "collections" "chapters" "records" id)
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-failure [:request-error evt-nm]}}))

(comment

  (>evt [:chapters/get])

  (>evt [:chapter/get "q52qprlb2geq5co"])

  (>evt [:chapter/create "The End" "Hello the end of the world, my name is cheese." ["hz5p7g21fca6k2w"]])

  (>evt [:chapter/delete "f72goy1ol1uivuy"])

  (>evt [:chapter/update "q52qprlb2geq5co" :title "New End"])

  (<sub [:db])

  (>evt [:initialize-db])

 nil)
