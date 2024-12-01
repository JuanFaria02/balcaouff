FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-23-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:23-slim

EXPOSE 8080

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
