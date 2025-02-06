FROM maven:3.9.0-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-slim
#COPY . .
#RUN mvn clean package -DskipTests
COPY /target/password-generator-1.0.0-SNAPSHOT.jar password-generator-1.0.0-SNAPSHOT.jar
#ENV PORT=8080
EXPOSE 8080
#ARG JAR_FILE=target/password-generator-1.0.0-SNAPSHOT.jar
#ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","password-generator-1.0.0-SNAPSHOT.jar"]