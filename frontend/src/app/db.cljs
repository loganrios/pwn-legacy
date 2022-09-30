(ns app.db
  (:require [re-frame.core :refer [reg-event-db
                                   reg-sub
                                   subscribe
                                   dispatch]]))

(def <sub (comp deref subscribe))
(def >evt dispatch)

(def dev-db {:user/email ""
             :user/id ""
             :token ""
             :users {:Taz #:user{:username "Tazspeare"
                                 :privilege :author
                                 :bio "My name is Taz Michael Mann, fear my name!"
                                 :image "https://avatarfiles.alphacoders.com/594/59437.jpg"
                                 :readingList "https://projectwebnovel.com/Taz/reading-list"
                                 :links {"Discord" "https://discord.gg/fMQbrSSS"}
                                 :following #{:Leif :Mazzy}
                                 :sponsoring {:Underground-72 2000 :Devrey 15000}
                                 :reader-preferences {:adult-content false
                                                      :track-progress true}
                                 :id "test"}
                     :Leif #:user{:username "King-of-Basketball"
                                  :privilege :author
                                  :bio "Poetry is for nerdz"
                                  :image ""
                                  :following #{:Ash :Mazzy :Devrey}
                                  :sponsoring {:Mazzy 100
                                               :Ash 50
                                               :Taz 1}
                                  :reader-preferences {:adult-content false
                                                       :track-progress false}}
                     :Devrey #:user{:username "Queen"
                                    :privilege :author
                                    :bio "If you know, you know"
                                    :image ""
                                    :following #{:Leif :Ash}
                                    :sponsoring {:Leif 1500
                                                 :Taz 2}
                                    :reader-preferences {:adult-content false
                                                         :track-progress false}}}
             :works {1 #:work{:title "World of Broken Dreams"
                              :owner :Taz
                              :visibility :public
                              :status :completed
                              :contributors []
                              :blurb "This is a bunch of dummy text."
                              :warnings ["Traumatizing Content"]
                              :genres ["Fantasy" "Romance"]
                              :tags ["Action" "Drama" "LitRPG" "Magic" "Tragedy"]
                              :chapters {1 :TheBeginning
                                         2 :TheDayAfterTomorrow
                                         3 :Aftermorrow}
                              :cover "https://www.royalroadcdn.com/public/covers-large/world-of-broken-dreams-63504.jpg?time=1661125629"
                              :hits 9}
                     2 #:work{:title "Black Reflections"
                              :owner :Taz
                              :visibility :public
                              :status :ongoing
                              :contributors []
                              :blurb "This is also a bunch of dummy text."
                              :warnings ["Traumatizing Content"]
                              :genres ["Psychological" "Horror"]
                              :tags ["Action" "Drama" "LitRPG" "Magic" "Tragedy" "Romance" "Fantasy"]
                              :chapters {1 :Zombies
                                         2 :MartialArtistOrksinSpace
                                         3 :TillSheWasGone}
                              :cover "https://www.royalroadcdn.com/public/covers-large/world-of-broken-dreams-63504.jpg?time=1661125629"
                              :hits 2}
                     3 #:work{:title "Become Stock Leviathan"
                              :owner :Devrey
                              :visibility :public
                              :status :hiatus
                              :contributors []
                              :blurb "Only a Queen can rule the world."
                              :warnings ["Adult Content" "Gore"]
                              :genres ["Contemporary" "Nonfiction"]
                              :tags ["Action" "Drama" "Spicy" "Magic" "Economic" "Realistic" "Autobiography"]
                              :chapters {1 :OfStocksandDemons
                                         2 :BorntobeRich
                                         3 :AshinaBrokenWorld
                                         4 :CorporateZombiesAfantasticalmetaphorforexploitationandsocialdestruction
                                         5 :Queen}
                              :cover "https://www.royalroadcdn.com/public/covers-large/become-leviathan-61244.jpg?time=1656233092"
                              :hits 489}}
             :chapters {:TheBeginning #:chapter{:title "The Beginning"
                                                :content "This is a really long string of text."
                                                :authors [:Taz]
                                                :pre-content "Sup dogs."
                                                :post-content "Double sup dogs."
                                                :hits 10
                                                :early-access false}
                        :TheDayAfterTomorrow #:chapter{:title "The Day After Tomorrow"
                                                       :content "This is a string of text."
                                                       :authors [:Taz]
                                                       :pre-content "Heyo"
                                                       :post-content "I am the strongest worder."
                                                       :hits 12
                                                       :early-access false}
                        :Aftermorrow #:chapter{:title "Aftermorrow"
                                               :content "This is a less long string of text."
                                               :authors [:Taz]
                                               :pre-content ""
                                               :post-content ""
                                               :hits 3
                                               :early-access false}}})

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
