(ns modules.infra.kafka
  (:gen-class)
  (:require [modules.configurations.kafka_config :as config])
  (:import [org.apache.kafka.clients.producer KafkaProducer ProducerRecord]))

(defn send-data [event-key event-body]
  (def config {"value.serializer" "org.apache.kafka.common.serialization.StringSerializer"
               "key.serializer" "org.apache.kafka.common.serialization.StringSerializer"
               "bootstrap.servers" config/broken
               "acks" "all"})
  (def producer (KafkaProducer. config))
  (println "Enviando dados" event-body " para " config/broken " topico " config/topic)
  (def response (.send producer (ProducerRecord. config/topic (.toString event-body))))
  (println (.get response)))