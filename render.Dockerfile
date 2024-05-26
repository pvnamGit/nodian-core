FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim

EXPOSE 8080
COPY --from=build /build/libs/nodian-core.jar nodian-core.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","nodian-core.jar"]
