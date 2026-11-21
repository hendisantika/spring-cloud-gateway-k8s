# spring-cloud-gateway-k8s

[![Java CI with Maven](https://github.com/hendisantika/spring-cloud-gateway-k8s/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-cloud-gateway-k8s/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1-6DB33F?logo=springboot)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1-6DB33F?logo=spring)
![Kubernetes](https://img.shields.io/badge/Kubernetes-ready-326CE5?logo=kubernetes)

Sample microservices running on Kubernetes behind **Spring Cloud Gateway**, using
**Spring Cloud Kubernetes** for service discovery, load balancing and ConfigMap based configuration.

## Architecture

```
                 ┌──────────────────────────┐
  client ──────► │ gateway-service  :8000   │  Spring Cloud Gateway (WebFlux)
                 └────────────┬─────────────┘
              lb://category-service   lb://product-service
                 ┌────────────┴─────────────┐
                 ▼                          ▼
   ┌──────────────────────────┐   ┌──────────────────────────┐
   │ category-service   :80   │◄──│ product-service   :8081  │
   │ JPA + PostgreSQL         │   │ JPA + PostgreSQL + Feign │
   └──────────────────────────┘   └──────────────────────────┘
```

| Service            | Port | Description                                                                 |
|--------------------|------|-----------------------------------------------------------------------------|
| `gateway-service`  | 8000 | Routes `/category-service/**` and `/product-service/**` via Kubernetes discovery |
| `category-service` | 80   | CRUD for categories (PostgreSQL database `category`)                       |
| `product-service`  | 8081 | CRUD for products, validates category through OpenFeign (database `product`) |

## Tech Stack

- Java **25** (LTS)
- Spring Boot **4.1.x**
- Spring Cloud **2025.1.x**
  - `spring-cloud-starter-gateway-server-webflux`
  - `spring-cloud-starter-kubernetes-client-all` (discovery, config, load balancer)
  - `spring-cloud-starter-openfeign`
- Spring Data JPA + PostgreSQL (H2 for tests)
- Lombok
- Maven, Docker, Kubernetes

## Prerequisites

- JDK 25
- Maven 3.9+ (or the bundled `./mvnw`)
- PostgreSQL with databases `category` and `product`
- Docker and a local Kubernetes cluster (Minikube, Kind, Docker Desktop, …) for deployment

## Build & Test

Each service is an independent Maven project:

```bash
cd category-service && ./mvnw clean verify
cd product-service  && ./mvnw clean verify
cd gateway-service  && ./mvnw clean verify
```

Tests run with the `test` profile (`src/test/resources/application-test.yml`), which uses an
in-memory H2 database and disables Spring Cloud Kubernetes, so no PostgreSQL or cluster is needed.

## Run Locally

```bash
cd category-service && ./mvnw spring-boot:run
cd product-service  && ./mvnw spring-boot:run
```

Database connection settings are in each service's `src/main/resources/application.yml`.

## API Endpoints

Through the gateway (`http://localhost:8000`):

| Method | Path                                         | Description            |
|--------|----------------------------------------------|------------------------|
| POST   | `/category-service/api/v1/save`              | Create a category      |
| POST   | `/category-service/api/v1/delete/{id}`       | Soft-delete a category |
| GET    | `/category-service/api/v1/list`              | List categories (paged)|
| GET    | `/category-service/api/v1/get/{id}`          | Get category by id     |
| POST   | `/product-service/api/v1/save`               | Create a product       |
| GET    | `/product-service/api/v1/list`               | List products (paged)  |
| GET    | `/product-service/api/v1/get/{id}`           | Get product by id      |

Example:

```bash
curl -X POST http://localhost:8000/category-service/api/v1/save \
  -H 'Content-Type: application/json' \
  -d '{"name":"Electronics"}'

curl -X POST http://localhost:8000/product-service/api/v1/save \
  -H 'Content-Type: application/json' \
  -d '{"name":"Laptop","category_id":1,"price":15000000,"qty":10}'
```

## Docker

Images are based on `bellsoft/liberica-openjdk-debian:25`:

```bash
for svc in category-service product-service gateway-service; do
  (cd $svc && ./mvnw -DskipTests package && docker build -t $svc:latest .)
done
```

## Deploy to Kubernetes

```bash
kubectl apply -f category-service/k8s/namespace.yml

kubectl apply -f category-service/k8s/
kubectl apply -f product-service/k8s/
kubectl apply -f gateway-service/k8s/
```

Each service imports its ConfigMap through `spring.config.import: "optional:kubernetes:"`.
The gateway uses the Kubernetes discovery client, so its service account needs permission to
read `services`, `endpoints`, `pods` and `configmaps`.

## CI/CD & Dependency Management

- **GitHub Actions** (`.github/workflows/maven.yml`) builds and tests every service in a matrix on
  JDK 25 (Temurin) and submits the Maven dependency graph on pushes to `main`.
- **Dependabot** (`.github/dependabot.yml`) checks weekly (Monday 05:00 Asia/Jakarta) for:
  - Maven dependencies, grouped by Spring Boot, Spring Cloud, OpenFeign, test and minor/patch updates
  - Docker base images (JDK major upgrades ignored)
  - GitHub Actions

## Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: [@hendisantika34](https://t.me/hendisantika34)
- Link: [s.id/hendisantika](https://s.id/hendisantika)
