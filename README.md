# pc2u202515871

RESTful API for AquaMonitor. It registers water quality sampling plans for monitoring stations.

## Requirements

- Java 25
- MySQL running on port `3307` (user `root`, password `password`, schema `aqua_monitor`)

## Run

```bash
./mvnw spring-boot:run
```

Swagger UI: http://localhost:8080/swagger-ui/index.html

## Endpoint

`POST /api/v1/sampling-plans`

## Author

Alexis Sebastian Martin Farro - u202515871
