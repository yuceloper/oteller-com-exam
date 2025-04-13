FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests

RUN ls -l target/

FROM --platform=linux/amd64 openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
