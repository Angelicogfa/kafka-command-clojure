FROM clojure:lein AS base
WORKDIR /src/api
COPY . /src/api
RUN lein uberjar

FROM clojure:lein
ENV KAFKA_BROKEN=
ENV TOPIC_KAFKA=
COPY --from=base src/api/target/producer-0.0.1-standalone.jar producer-0.0.1-standalone.jar
CMD ["java", "-jar", "producer-0.0.1-standalone.jar"]