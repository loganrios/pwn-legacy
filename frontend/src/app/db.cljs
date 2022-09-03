(ns app.db
  (:require [re-frame.core :refer [reg-event-db
                                   reg-sub
                                   subscribe
                                   dispatch]]))

(def <sub (comp deref subscribe))
(def >evt dispatch)

(def dev-db {:jwt "Some string"
             :my-uuid :Taz
             :users {:Taz #:user{:username "Tazspeare"
                                 :privilege :author
                                 :bio "My name is Taz Michael Mann, fear my name!"
                                 :image "https://avatarfiles.alphacoders.com/594/59437.jpg"
                                 :readingList "https://projectwebnovel.com/Taz/reading-list"
                                 :links {"Discord" "https://discord.gg/fMQbrSSS"}
                                 :follows [:Leif :Mazzy]
                                 :sponsors {:Underground-72 2000}
                                 :reader-preferences {:adult-content false
                                                      :track-progress true}}
                     :Leif #:user{:username "King-of-Basketball"
                                  :privilege :author
                                  :bio "Poetry is for nerdz"
                                  :image ""
                                  :follows [:Leif :Ash :Mazzy]
                                  :sponsors {:Mazzy 100
                                             :Ash 50
                                             :Taz 1}
                                  :reader-preferences {:adult-content false
                                                       :track-progress false}}
                     :Devrey #:user{:username "Queen"
                                    :privilege :author
                                    :bio "If you know, you know"
                                    :image ""
                                    :follows [:Leif :Ash]
                                    :sponsors {:Leif 1500
                                               :Taz 2}
                                    :reader-preferences {:adult-content false
                                                         :track-progress false}}}})

(def prod-db {})

(reg-event-db :initialize-db
              (fn [db _]
                dev-db))
(reg-sub :db
         (fn [db _]
           db))

(comment

  (<sub [:db])

  nil)
