# CI manager
Simple app to manage passwords.
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

spring.datasource.initialization-mode=EMBEDDED
spring.datasource.schema=classpath*:database/schema.sql
spring.datasource.data=classpath*:database/data.sql
``` 
#### run
```bash
mvn clean spring-boot:run
```


