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

;;end TODO

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

(defn filter-name
  [name]
  (xt/q (xt/db node)
        '{:find [uuid]
          :where [[e :xt/id uuid]
                  [e :user/username name]]
          :in [name]}
        name))

(defn name->uuid [name] (ffirst (filter-name name)))

(defn remove-user [name]
  (remove-from-node node (name->uuid name)))

(comment

  (start-dev)

  (def node (get-in system [:db :db]))

  (def working-uuid (create-user "Tazspeare"))
  (remove-user "Tazspeare")

  (defn create-work [title username visibility]
    (let [uuid (random-uuid)]
    (xt/submit-tx node
                  [[::xt/put
                    {:xt/id uuid
                     :data-type :work
                     :work/title title
                     :work/owner (name->uuid username)
                     :work/visibility [visibility]
                     :work/contributors? [] ;; can edit or add chapters, but not delete
                     :work/blurb? ""
                     :work/warnings? []
                     :work/genres? []
                     :work/tags? []
                     :work/chapters? {} ;; will practically have at least one chapter
                     :work/cover? ""
                     :work/hits? 0}]])
    uuid))

  (defn create-chapter [title username content]
    (let [uuid (random-uuid)]
    (xt/submit-tx node
                  [[::xt/put
                    {:xt/id uuid
                     :data-type :chapter
                     :chapter/title title
                     :chapter/content content
                     :chapter/authors [(name->uuid username)]
                     :chapter/pre-content? "" ;; or maps with polls?
                     :chapter/post-content? "" ;; or maps with polls?
                     :chapter/comments? {}
                     :chapter/hits? 0
                     :chapter/early-access? false}]])
    uuid))

 (create-work "Black Reflections" "Tazspeare" :public)

 (create-chapter "Chapter 1" "Tazspeare" "Leif is the Max-Man")

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

  (remove-work "Tazspeare")

  (xt/sync node)

  (full-query node)

  (stop-dev)

  nil)
