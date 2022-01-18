FROM openjdk:17-alpine

WORKDIR /opt/server
COPY ./target/new-dancecamp-0.0.1-SNAPSHOT.jar server.jar

EXPOSE 8200

ENTRYPOINT ["java", "-jar", "server.jar"]