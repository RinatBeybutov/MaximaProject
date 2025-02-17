FROM openjdk:21-jdk-slim

WORKDIR /app

COPY userService-0.0.1-SNAPSHOT.jar /app/userService.jar

EXPOSE 8082

#ENV SPRING_PROFILES_ACTIVE=docker

ENTRYPOINT ["java", "-jar", "userService.jar"]