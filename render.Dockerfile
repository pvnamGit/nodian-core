FROM eclipse-temurin:17-jdk-focal as BUILD
VOLUME /tmp
WORKDIR /app
COPY . .
RUN ./gradlew clean build
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} nodian-core.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","nodian-core.jar"]
