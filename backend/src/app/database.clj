(ns app.database
  (:require [clojure.java.io :as io]
            [xtdb.api :as xt]
            [com.stuartsierra.component :as component]
            [com.stuartsierra.component.repl :refer [set-init]]))

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

(defn full-query
  [node]
  (xt/q
   (xt/db node)
   '{:find [(pull e [*])]
     :where [[e :xt/id id]]}))

(defn remove-from-node [node eid]
  (xt/submit-tx node [[::xt/evict eid]]))

(defn create-user [username]
  (let [uuid (random-uuid)]
    (xt/submit-tx node
                [[::xt/put
                  {:xt/id uuid
                   :data-type :user
                   :user/privilege []
                   :user/reading-list {}
                   :user/follows []
                   :user/sponsors []
                   :user/username username
                   :user/reader-preferences {}}]])
    uuid))


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

(comment

  (start-dev)

  (def node (get-in system [:db :db]))


  (create-user "Leif the Max")

  (def working-uuid (create-user "Tazspeare"))

  (remove-from-node node working-uuid)

  (xt/q (xt/db node)
        '{:find [name user-id]
          :where [[e :xt/id user-id]
                  [e :username name]]})


    (defn filter-name
    [name]
    (xt/q (xt/db node)
          '{:find [uuid]
            :where [[e :xt/db uuid]
                    [e :user/username name]]
            :in [name]}
            name))

   (filter-name "Tazspeare")

  (xt/submit-tx node
                [[::xt/put
                  {:data-type :work
                   :work/title ""
                   :work/owner account-id
                   :work/visibility [:public :private :restricted]
                   :work/contributors? [account-id*] ;; can edit or add chapters, but not delete
                   :work/blurb? ""
                   :work/warnings? []
                   :work/genres? []
                   :work/tags? []
                   :work/chapters? {chapter-id index} ;; will practically have at least one chapter
                   :work/cover? ""
                   :work/hits? 0}]])

  (xt/sync node)

  (full-query node)

  (stop-dev)

  nil)
