(ns handlers.core
    (:gen-class)
    (:require [modules.infra.kafka :as kafka]))

(defn -main []
    (kafka/send-data nil {:context "sales-context" :message "Venda gerada pelo ID 123" :value 125.53 :date "2019-09-18T23:19:21"}))