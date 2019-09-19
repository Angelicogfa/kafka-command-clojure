(defproject producer "0.0.1"
    :description "API para envio de eventos"
    :dependencies [[org.clojure/clojure "1.10.0"]
                   [org.apache.kafka/kafka-clients "1.1.0"]]
    :main handlers.core
    :aot [handlers.core])