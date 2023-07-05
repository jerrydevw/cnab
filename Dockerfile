FROM openjdk:17-alpine
MAINTAINER baeldung.com
COPY target/bycoders-0.0.1-SNAPSHOT.jar cnab-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/cnab-1.0.0.jar"]