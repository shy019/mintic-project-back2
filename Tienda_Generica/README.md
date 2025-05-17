# Generic Store

Repository: https://github.com/shy019/mintic-project-back2/tree/master/Tienda_Generica

The **Generic Store** backend application running in *dev* environment, built with Spring Boot 3.2.5 and Java 21. Includes **Swagger UI** for interactive API documentation and testing.

---

## 📋 Table of Contents
1. [Overview](#overview)
2. [Features](#features)
3. [Architecture](#architecture)
4. [Prerequisites](#prerequisites)
5. [Installation](#installation)
6. [Configuration](#configuration)
7. [Running with Docker](#running-with-docker)
8. [REST Endpoints & Swagger](#rest-endpoints--swagger)
9. [Postman Collection](#postman-collection)
10. [Unit Tests & Coverage](#unit-tests--coverage)
11. [Metrics & Reports](#metrics--reports)
12. [Security](#security)
13. [Code Conventions](#code-conventions)
14. [Project Structure](#project-structure)
15. [Contributing](#contributing)
16. [License](#license)

---

## Overview
Generic Store is a REST API that manages:
- Users (signup, login)
- Branches
- Suppliers
- Products
- Sales and sale details
- USD to COP exchange rate retrieval with fault tolerance (Resilience4j Retry & CircuitBreaker)

The service uses **MongoDB** as the datastore.

## Features
- Spring Boot 3.2.5 & Java 21
- MongoDB (NoSQL)
- WebClient for external exchange rate API consumption
- Resilience4j for retry and circuit breaker
- Spring Retry configured via application.properties
- JWT-based stateless authentication
- Swagger UI available at `/swagger-ui.html`
- Docker & Docker Compose support
- Unit tests with JUnit 5 & Mockito
- Code coverage reports with JaCoCo

## Architecture
```
[Client] --> [Spring Security Filter Chain] --> [REST Controllers] --> [Services] --> [MongoDB Repositories]
                         | 
                         +--> [AuthTokenFilter] (JWT validation)
                         +--> [Resilience4j interceptors]
```

## Prerequisites
- Java 21 SDK
- Maven 3.6+
- Docker & Docker Compose
- MongoDB (local or container)

## Installation
```bash
# Clone the repository
git clone https://github.com/shy019/mintic-project-back2.git
cd Tienda_Generica

# Build the application
mvn clean package -DskipTests
```

## Configuration
Edit `src/main/resources/application.properties`:
```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=generic_store

mintic.webclient.baseurl=https://api.exchangerate-api.com/v4/latest/
# Retry configuration
mintic.retry.maxAttempts=5
mintic.retry.delay=1500
mintic.retry.multiplier=1.5
# JWT settings
mintic.app.jwtSecret=genericstoreV2
mintic.app.jwtExpirationMs=864000000
# CircuitBreaker settings
resilience4j.circuitbreaker.instances.exchangeRateCB.slidingWindowSize=5
resilience4j.circuitbreaker.instances.exchangeRateCB.failureRateThreshold=50
resilience4j.circuitbreaker.instances.exchangeRateCB.waitDurationInOpenState=10s
```

## Running with Docker
```bash
docker-compose up --build
```
- App: `http://localhost:8080`
- MongoDB: `mongodb://localhost:27017`

## REST Endpoints & Swagger
Interactive documentation is available at:  
`http://localhost:8080/swagger-ui.html`  
(API definition in JSON at `/v3/api-docs`)

| Resource           | Method   | Path                                   | Authentication |
|--------------------|----------|----------------------------------------|----------------|
| Signup             | POST     | `/api/generic-store/auth/signup`       | None           |
| Signin             | POST     | `/api/generic-store/auth/signin`       | None           |
| Users              | GET      | `/api/generic-store/user/`             | Bearer JWT     |
| Branches           | GET/POST | `/api/generic-store/branch/`           | Bearer JWT     |
| Suppliers          | GET/POST | `/api/generic-store/supplier/`         | Bearer JWT     |
| Products           | GET/POST | `/api/generic-store/product/`          | Bearer JWT     |
| Sales              | GET/POST | `/api/generic-store/sale/`             | Bearer JWT     |
| Sale Details       | GET/POST | `/api/generic-store/saledetail/`       | Bearer JWT     |

## Postman Collection
Import `generic_store_postman_collection.json` located at the project root. It includes an environment with `base_url` and `jwt_token`.

## Unit Tests & Coverage
```bash
mvn clean test
mvn verify
```
- Test report: `target/site/surefire-report.html`
- Coverage report: `target/site/jacoco/index.html`

## Metrics & Reports
- Surefire HTML report for unit tests
- JaCoCo coverage reports (HTML, XML, CSV)

## Security
- Public endpoints: `/api/generic-store/auth/signup`, `/api/generic-store/auth/signin`
- All other endpoints require `Authorization: Bearer <token>`
- JWT expiration: 10 days
- Custom `AuthEntryPointJwt` handles unauthorized errors

## Code Conventions
- Lombok for boilerplate code
- Packages: `config`, `controller`, `service`, `repository`, `dto`, `exception`, `utils`
- Constants in UPPER_SNAKE_CASE

## Project Structure
```
src/
└─ main/
   └─ java/com/mintic/genericstore/
      ├─ config/
      ├─ controller/
      ├─ dto/
      ├─ exception/
      ├─ service/
      ├─ repository/
      └─ utils/constants
└─ resources/
   └─ application.properties
test/
```

## Contributing
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Open a Pull Request

## License
This project is licensed under the MIT License.
