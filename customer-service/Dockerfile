FROM openjdk:17-jdk-slim

WORKDIR /app

# Install curl for healthcheck
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

COPY target/customer-service-0.0.1-SNAPSHOT.jar customer-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "customer-service.jar"]
