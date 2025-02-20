FROM openjdk:21-jdk-slim

WORKDIR /app

COPY orderService-0.0.1-SNAPSHOT.jar /app/orderService.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "orderService.jar"]