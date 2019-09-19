(ns handlers.core
  (:gen-class)
  (:import [org.apache.kafka.clients.consumer KafkaConsumer ConsumerRecord OffsetAndMetadata]))

(defn- consumer-record->map
  [^ConsumerRecord record]
  {:checksum              (.checksum record)
   :key                   (.key record)
   :offset                (.offset record)
   :partition             (.partition record)
   :serialized-key-size   (.serializedKeySize record)
   :serialized-value-size (.serializedValueSize record)
   :timestamp             (.timestamp record)
   :timestamp-type        (.timestampType record)
   :topic                 (.topic record)
   :value                 (.value record)
   :consumer-record       record})

(defn commit-message-offset [consumer message]
  (when (and consumer message)
    (let [commit-point (long (inc (.offset ^ConsumerRecord message)))]
      (.commitAsync consumer)
      (println "Mensagem commitada"))))

(defn- processevent [consumer record]
  (let [context {:consumer consumer
                 :message (consumer-record->map record)}]
    (println "Process event: " (:message context))
    (try
      (do
        (println (:message context))
        (commit-message-offset consumer record))
      (catch Exception e (println (.getMessage e))))))

(defn -main []
  (def config {"bootstrap.servers" "localhost:9092"
               "group.id" "sales-domain"
               "enable.auto.commit" "false"
               "value.deserializer" "org.apache.kafka.common.serialization.StringDeserializer"
               "key.deserializer" "org.apache.kafka.common.serialization.StringDeserializer"})

  (def consumer (doto (KafkaConsumer. config)
                  (.subscribe ["topic-sample"])))

  (while true
    (let [^ConsumerRecord records (.poll consumer 100)]
      (doseq [record records]
        (do
          (processevent consumer record))))))