FROM clojure:lein AS base
WORKDIR /src/api
COPY . /src/api
RUN lein uberjar

FROM clojure:lein
ENV KAFKA_BROKEN=
ENV TOPIC_KAFKA=
ENV GROUP_ID=
COPY --from=base src/api/target/consumer-0.0.1-standalone.jar consumer-0.0.1-standalone.jar
CMD ["java", "-jar", "consumer-0.0.1-standalone.jar"]