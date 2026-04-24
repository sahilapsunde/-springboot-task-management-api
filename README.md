# Spring Boot Task Management API
Production-ready RESTful microservice with JWT authentication, RBAC,
MySQL persistence, Kafka event-driven notifications, and Kubernetes deployment on AWS EC2.
## Features- JWT Authentication & Role-Based Access Control (RBAC)- Kafka event-driven task notifications- MySQL persistence with JPA/Hibernate- Docker containerized, Kubernetes orchestrated- Rate limiting, circuit breaker, API versioning- Centralized error handling
## Tech Stack
Java 8+ · Spring Boot · Spring Security · JWT · MySQL · Kafka · Kubernetes · Docker · AWS EC2
## Quick Start
```bash
git clone https://github.com/sahilapsunde/springboot-task-management-api.git
cd springboot-task-management-api
docker-compose up -d
```
## API Endpoints
| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | /api/v1/auth/register | Public | Register user |
| POST | /api/v1/auth/login | Public | Get JWT token |
| GET | /api/v1/tasks | USER/ADMIN | List tasks |
| POST | /api/v1/tasks | USER/ADMIN | Create task |
| PUT | /api/v1/tasks/{id} | USER/ADMIN | Update task |
| DELETE | /api/v1/tasks/{id} | ADMIN | Delete task |
