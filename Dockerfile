FROM eclipse-temurin:17-jdk-focal
VOLUME /tmp
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} nodian-core.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","nodian-core.jar"]
