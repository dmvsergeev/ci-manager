# CI manager
Simple app to manage application passwords.

Additional: news, guides, etc ...

### Testing
NOTE: please install docker on a CI-server
```bash
mvn clean test
```

### First start
#### create config/application.properties
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cimanager
spring.datasource.username=dbuser
spring.datasource.password=dbpassword12345

spring.datasource.driver-class-name=org.postgresql.Driver

spring.datasource.initialization-mode=ALWAYS
spring.datasource.schema=classpath*:database/schema.sql
spring.datasource.data=classpath*:database/data.sql
``` 

#### test database
```bash
docker run -d \
    --name cimanager-postgres \
    -e POSTGRES_PASSWORD=dbpassword12345 \
    -e POSTGRES_USER=dbuser \
    -e POSTGRES_DB=cimanager \
    -p 5432:5432 \
postgres
```

#### run
```bash
mvn clean spring-boot:run -DskipTests=true
```

## web
### login
```
login=admin
password=admin
```
```
login=user
password=user
```