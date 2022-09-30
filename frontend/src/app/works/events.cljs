(ns app.works.events
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
 :works/get
 (fn [{:keys [db]} [evt-nm]]
   {:http-xhrio {:method :get
                 :uri (endpoint "collections" "works" "records")
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-success [:works/get-success]
                 :on-failure [:request-error evt-nm]}}))

(reg-event-db
 :works/get-success
 (fn [db [_ {:keys [items]}]]
   (-> db
    (assoc :works (vec->idxent (map (fn [item] (map->nsmap item "work")) items) :work/id)))))


(reg-event-fx
 :work/create
 (fn [{:keys [db]} [evt-nm title owner visibility hits status]]
   {:http-xhrio {:method :post
                 :uri (endpoint "collections" "works" "records")
                 :params {:title title :owner owner :visibility visibility :hits hits :status status}
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-failure [:request-error evt-nm]}}))


;; (reg-event-fx
;;  :work/get
;;  (fn [{:keys [db]} [evt-nm id]]
;;    {:http-xhrio {:method :get
;;                  :uri (endpoint "collections" "works" "records" :id)
;;                  :params {:id id}
;;                  :format (json-request-format)
;;                  :response-format (json-response-format {:keywords? true})
;;                  :on-success [:work/get-success]
;;                  :on-failure [:request-error evt-nm]}}))

;; (reg-event-db
;;  :work/get-success
;;  (fn [db [_ {:keys [id created updated title owner visibility contributors blurb chapters cover hits status]}]]
;;    (-> db
;;        (assoc :work/id id)
;;        (assoc :work/created created)
;;        (assoc :work/updated updated)
;;        (assoc :work/title title)
;;        (assoc :work/owner owner)
;;        (assoc :work/visibility visibility)
;;        (assoc :work/contributors contributors)
;;        (assoc :work/blurb blurb)
;;        (assoc :work/chapters chapters)
;;        (assoc :work/cover cover)
;;        (assoc :work/hits hits)
;;        (assoc :work/status status))))

(comment)


(reg-event-fx
 :work/update
 (fn [{:keys [db]} [evt-nm id title]]
   {:http-xhrio {:method :patch
                 :uri (endpoint "collections" "works" "records" ":id")
                 :params {:id id :title title}
                 :format (json-request-format)
                 :response-format (json-response-format {:keywords? true})
                 :on-failure [:request-error evt-nm]}}))

(>evt [:work/update "w4nx6ag9xuvjccu" "I am become Hell"])

   ;; "1uig93r3vda08kt"

(>evt [:works/get])

(>evt [:work/get "dscgb2ve6my390s"])

(>evt [:work/create "Black Reflections" "hz5p7g21fca6k2w" "public" "1" "ongoing"])


(<sub [:db])

(>evt [:initialize-db])



nil
