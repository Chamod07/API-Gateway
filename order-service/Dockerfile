FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/order-service-0.0.1-SNAPSHOT.jar order-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "order-service.jar"]
