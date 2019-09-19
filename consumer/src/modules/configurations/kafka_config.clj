(ns modules.configurations.kafka_config
  (:gen-class))

(def broken
  (or (System/getenv "KAFKA_BROKEN") "localhost:9092"))

(def topic
  (or (System/getenv "TOPIC_KAFKA") "topic-sample"))

(def groupid
  (or (System/getenv "GROUP_ID") "group-sample"))