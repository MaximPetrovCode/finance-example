# Stages

### 1. Build instances
`./gradlew bootJar && cd ./front/ && npm run build`

### 2. Build containers:
`docker-compose up --build`

### 3. Or run using docker composer
`docker-compose up`

### Frontend
[http://localhost:8080/](http://localhost:8080/)

### Server APIs
[http://localhost:9090/values](http://localhost:9090/values)

[http://localhost:9090/currency](http://localhost:9090/currency)
