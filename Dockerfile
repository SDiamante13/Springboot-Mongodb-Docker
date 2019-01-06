FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8085
ADD build/libs/springboot-mongodb-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongo/test", "-jar", "app.jar"]

