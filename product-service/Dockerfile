FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/product-service-0.0.1-SNAPSHOT.jar product-service.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "product-service.jar"]
