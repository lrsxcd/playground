(ns playground.py
  (:require [libpython-clj.python :as py]))

(py/initialize!)

(defonce logging
  (py/import-module "logging"))

(def logger
  (py/call-attr logging "getLogger"))

(py/call-attr-kw logger "error" ["1"] {})

