FROM openjdk:17-jdk-slim

WORKDIR /app

# Install curl for healthcheck
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

COPY target/product-service-0.0.1-SNAPSHOT.jar product-service.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "product-service.jar"]
