FROM openjdk:17-jdk-alpine

COPY target/synergy-api-docker.jar synergy-api-docker.jar

ENTRYPOINT [ "java", "-jar", "synergy-api-docker.jar" ]