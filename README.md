# **🌐Scalable API Gateway with Real-Time Monitoring**

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker">
  <img src="https://img.shields.io/badge/Kubernetes-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white" alt="Kubernetes">
  <img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=white" alt="React">
  <img src="https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white" alt="Prometheus">
  <img src="https://img.shields.io/badge/ELK%20Stack-005571?style=for-the-badge&logo=elastic-stack&logoColor=white" alt="ELK Stack">
</div>

---
## **📖Project Description**
This project is a **cloud-native API Gateway** designed to serve as a central point for routing, securing, and monitoring API traffic in a microservices architecture. It provides seamless integration with multiple backend services, real-time analytics, and scalability features. The gateway routes requests to microservices like `customer-service`, `order-service`, and `product-service` while ensuring security, rate limiting, and centralized monitoring.

### **🔑Key Features**
- **API Management**:
    - Authentication and Authorization using OAuth 2.0 and JWT.
    - Rate limiting to manage client quotas.
    - Auto-generated API documentation using Swagger/OpenAPI.
- **Real-Time Monitoring**:
    - Traffic metrics such as request count, response time, and error rates.
    - User-friendly dashboard built with React.
    - Alerts for unusual traffic spikes or high error rates.
- **Scalability**:
    - Containerized services using Docker.
    - Kubernetes orchestration for horizontal scaling.
    - Dynamic service discovery with Consul/Eureka.
- **Logging & Tracing**:
    - Centralized logging using ELK Stack (Elasticsearch, Logstash, Kibana).
    - Distributed tracing with Jaeger/Zipkin.

---

## **🚀Installation**

### **Prerequisites**
1. Install [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/).
2. Install [Java JDK (11 or higher)](https://adoptopenjdk.net/).
3. Install [Node.js](https://nodejs.org/).
4. Install [Kubernetes CLI (kubectl)](https://kubernetes.io/docs/tasks/tools/).

### **Steps**
1. Clone the repository:
```
git clone https://github.com/Chamod07/API-Gateway.git
cd API-Gateway
```
2. Build the services:

```
./mvnw clean install
```

3. Start the services using Docker Compose:
```
docker-compose up --build
```

4. Access the API Gateway at `http://localhost:8080`.

---

## **⚙️Usage**

### **API Gateway Configuration**
The gateway routes requests to the following microservices:
- `customer-service`: `http://localhost:8081/api/customers/**`
- `order-service`: `http://localhost:8082/api/orders/**`
- `product-service`: `http://localhost:8083/api/products/**`

Example request via Postman or curl:

```
curl http://localhost:8080/api/customers/1
```

### **Monitoring**
- Prometheus metrics are available at `/actuator/prometheus`.
- Access logs in Kibana by connecting to Elasticsearch.

---

## **✨Features**

### **Microservices**
1. **Customer Service**: Manages customer data.
2. **Order Service**: Handles order processing.
3. **Product Service**: Manages product catalog.

### **Gateway Features**
- Centralized routing and security.
- Real-time traffic monitoring.

### **Dashboard**
- Visualizes system health and traffic metrics.

---

## **🤝Contributing**

I welcome contributions! To contribute:
1. Fork this repository.
2. Create a new branch for your feature or bug fix:
```
    git checkout -b feature-name
```
3. Commit your changes and push them to your forked repository.
4. Submit a pull request with a detailed description of your changes.

---

## **📜License**

This project is licensed under the Apache License. See the [LICENSE](./LICENSE) file for details.

---

## **🎨Enhancements**

### Badges

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

### Screenshots
Screenshots of the dashboard or API usage examples here.

### Documentation
For detailed documentation on API endpoints, refer to the Swagger/OpenAPI documentation available at `/swagger-ui.html`.

---

## **📅Changelog**

| Version | Date     | Changes                          |
|---------|----------|----------------------------------|
| v1.0    | Dec 2024 | Initial release with core features |

---
