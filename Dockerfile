FROM maven:3-openjdk-17

COPY pom.xml .
COPY src src

RUN mvn clean package

EXPOSE 8080
ENTRYPOINT ["java","-jar","target/bycoders-0.0.1-SNAPSHOT.jar"]