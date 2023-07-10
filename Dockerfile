FROM openjdk:17-alpine
MAINTAINER jerry.dev

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean package
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/bycoders-0.0.1-SNAPSHOT.jar"]