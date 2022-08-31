(ns app.db
  (:require [re-frame.core :refer [reg-event-db
                                   reg-sub
                                   subscribe
                                   dispatch]]))

(def <sub (comp deref subscribe))
(def >evt dispatch)

(def dev-db {:jwt "Some string"
             :my-uuid :Taz
             :Taz #:user{:username "Tazspeare"
                         :privilege :author
                         :bioText "My name is Taz Michael Mann, fear my name!"
                         :image "https://avatarfiles.alphacoders.com/594/59437.jpg"
                         :readingList "https://projectwebnovel.com/Taz/reading-list"
                         :links {"Personal Site" "https://www.tazthemann.com"
                                 "Discord" "https://discord.gg/fMQbrSSS"
                                 "Facebook" "https://www.facebook.com/kjforthman/"
                                 }
                         :follows [:Leif :Mazzy]
                         :sponsors [:Underground-72]
                         :reader-preferences {:adult-content false :track-progress true}}
             :Leif #:user{:username "King-of-Basketball"
                         :privilege :author
                         :bioText "Poetry is for nerdz"
                         :image ""
                         :follows [:Leif :Ash :Mazzy]
                         :sponsors []
                         :reader-preferences {:adult-content false :track-progress false}}
             :Devrey #:user{:username "Queen"
                         :privilege :author
                         :bioText "If you know, you know"
                         :image ""
                         :follows [:Leif :Ash]
                         :sponsors [:Leif]
                         :reader-preferences {:adult-content false :track-progress false}}})

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
