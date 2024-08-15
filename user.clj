(ns user
  (:require [nextjournal.clerk :refer [show! serve! halt!]]))

(serve! {:browse? true
         :watch-paths ["src"]})
#_(show! "src/presentacion.clj")

(comment
  (halt!)
  )