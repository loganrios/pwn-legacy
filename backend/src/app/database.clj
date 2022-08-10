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
  nil)
