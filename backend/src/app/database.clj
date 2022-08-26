(ns app.database
  (:require [clojure.java.io :as io]
            [xtdb.api :as xt]
            [com.stuartsierra.component :as component]
            [com.stuartsierra.component.repl :refer [set-init]]
            [clojure.spec.alpha :as s]))

(defrecord Database [dir]
  component/Lifecycle

  (start [this]
    (println "db: initializing...")
    (letfn [(kv-store [dir]
              {:kv-store {:xtdb/module 'xtdb.rocksdb/->kv-store
                          :db-dir (io/file dir)
                          :sync? true}})
            (prepend-dir [file-str]
              (str dir "/" file-str))]
      (let [db (xt/start-node
                {:xtdb/tx-log (kv-store (prepend-dir "tx-log"))
                 :xtdb/document-store (kv-store (prepend-dir "doc-store"))
                 :xtdb/index-store (kv-store (prepend-dir "index-store"))})]
        (assoc this :db db))))

  (stop [this]
    (println "db: stopping...")
    (.close (get this :db))
    (assoc this :db nil)))

(defn new-database [dir]
  (map->Database {:dir dir}))

;; TODO move to system.clj
(defn example-system [config-options]
  (let [{:keys [db-dir]} config-options]
    (component/system-map
     :db (new-database db-dir))))

(def system (example-system {:db-dir "data/dev"}))

(defn start-dev []
  (alter-var-root #'system component/start))

(defn stop-dev []
  (alter-var-root #'system component/stop))

;; end TODO

;;node
(def node (get-in system [:db :db]))

;;spec
(s/def :xt/id int?)
(s/def :data/type keyword?)

(s/def :user/username string?)
(s/def :user/privilege #{:author :admin})
(s/def :user/follows (s/coll-of uuid? :distinct true :into []))
(s/def :user/sponsors (s/coll-of uuid? :distinct true :into []))
(s/def :user/reader-preferences (s/map-of keyword? string? :distinct true :into {}))
            ;;18+ mode and/or track progress toggled (true or false)

(s/def :user/account (s/keys :req [:xt/id :data/type :user/username :user/reader-preferences]
                             :opt [:user/privilege :user/follows  :user/sponsors]))

(s/def :work/title string?)
(s/def :work/owner string?)
(s/def :work/visibility #{:public :private :restricted})
(s/def :work/contributors (s/coll-of uuid? :distinct true :into [])) ;; can edit or add chapters, but not delete
(s/def :work/blurb string?)
(s/def :work/warnings (s/coll-of string? :distinct true :into []))
(s/def :work/genres (s/coll-of string? :min-count 0 :max-count 2  :distinct true :into []))
(s/def :work/tags (s/coll-of string? :min-count 0 :max-count 20 :distinct true :into []))
(s/def :work/chapters (s/map-of int? uuid? :distinct true :into {})) ;; will practically have at least one chapter
(s/def :work/cover string?)
(s/def :work/hits int?)

(s/def :xt/id int?)
(s/def :data/type keyword?)
(s/def :work/work (s/keys :req [:xt/id :data/type :work/title :work/owner :work/visibility]
                          :opt [:work/contributors :work/blurb :work/warnings :work/genres
                                :work/tags :work/chapters :work/cover :work/hits]))

;;xtdb
(defn full-query
  [node]
  (xt/q
   (xt/db node)
   '{:find [(pull e [*])]
     :where [[e :xt/id id]]}))

(defn remove-from-node [node eid]
  (xt/submit-tx node [[::xt/evict eid]]))

(defn new-account
  [account-info]
  {:pre [(s/valid? :user/account account-info)]}
  (xt/submit-tx node [[::xt/put
                       account-info]]))

(defn new-work
  [work-info]
  {:pre [(s/valid? :work/work work-info)]
   :post [s/valid? string? %]}
  (xt/submit-tx node [[::xt/put
                       work-info]]))

;; (defn create-user [username]
;;   (let [uuid (random-uuid)]
;;     (xt/submit-tx node
;;                   [[::xt/put
;;                     {:xt/id uuid
;;                      :data-type :user
;;                      :user/privilege []
;;                      :user/reading-list {}
;;                      :user/follows []
;;                      :user/sponsors []
;;                      :user/username username
;;                      :user/reader-preferences {}}]])
;;     uuid))

;; (defn filter-name
;;   [name]
;;   (xt/q (xt/db node)
;;         '{:find [uuid]
;;           :where [[e :xt/id uuid]
;;                   [e :user/username name]]
;;           :in [name]}
;;         name))

;; (defn name->uuid [name] (ffirst (filter-name name)))

;; (defn remove-user [name]
;;   (remove-from-node node (name->uuid name)))

(comment

  (start-dev)

  (def working-uuid (create-user "Tazspeare"))

  (s/conform even? 1000)

  (new-account {:xt/id 3
                :data/type :user
                :user/username "Leifor"
                :user/privilege :admin
                :user/reader-preferences {:18+ "false" :track-progress "true"}})

  (new-work {:xt/id 1
             :data/type :work
             :work/title "World of Broken Dreams"
             :work/owner "kjforthman"
             :work/visibility :public
             :work/genres ["Romance" "Fantasy"]})

  (defn get-work-id-for-user
    [username]
    (xt/q (xt/db node)
          '{:find [uuid]
            :where [[e :work/owner username]
                    [e :work/title title]
                    [e :xt/id uuid]]
            :in [username]}
          (name->uuid username)))

  (get-work-id-for-user "Tazspeare")

  (defn remove-work [name]
    (remove-from-node node (ffirst (get-work-id-for-user name))))


  (xt/sync node)

  (full-query node)

  (stop-dev)

  nil)
