(ns spec
  (:require [clojure.spec.alpha :as s]))

(s/def :rlist/work-id uuid?)
(s/def :rlist/completion-status #{:planned :in-progress :on-hold :complete :dropped})
(s/def :rlist/visibility #{:public :private})
(s/def :rlist/progress int?)
(s/def :rlist/note string?)
(s/def :rlist/clout (s/coll-of uuid? :distinct true :into []))
(s/def :rlist/start-date inst?)
(s/def :rlist/last-read inst?)
(s/def :rlist/body string?)
(s/def :rlist/score (s/int-in 0 10))
(s/def :rlist/detailed-score (s/map-of string? int? :distinct true :into {}))
(s/def :rlist/item
  (s/keys :req [:rlist/work-id
                :rlist/completion-status
                :rlist/visibility]
          :opt [:rlist/progress
                :rlist/note
                :rlist/clout
                :rlist/start-date
                :rlist/last-read
                :rlist/body
                :rlist/score
                :rlist/detailed-score]))

(s/def :user/rlist (s/coll-of #(s/valid? :rlist/item %) :distinct true :into []))

(def user
  {:user/email "blah@blah.com"
   :user/rlist []})

(s/valid?
 :user/rlist
 [])
;; => true

{:user/id :kyle
 :prefs/new-ui? true}

{:user/id :default
 :prefs/new-ui? false}
