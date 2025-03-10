FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/auth-service-*.jar app.jar

EXPOSE 9000

HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
    CMD curl -f http://localhost:9000/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
