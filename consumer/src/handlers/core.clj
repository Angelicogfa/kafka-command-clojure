(ns handlers.core
  (:gen-class)
  (:require [modules.infra.kafka :as kafka]))

(defn -main []
  (kafka/handler (fn [event]
                   (println (:value event)))))