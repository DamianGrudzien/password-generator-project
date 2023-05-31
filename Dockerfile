FROM openjdk:17
ENV PORT=8080
EXPOSE 8080
ARG JAR_FILE=target/password-generator-1.0.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]