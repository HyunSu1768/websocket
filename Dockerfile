FROM openjdk:19
COPY build/libs/websocket-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]