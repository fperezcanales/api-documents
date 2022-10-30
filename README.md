## Personal project to practice:
* SpringBoot with Thymeleaf to create a ``Factura PDF``
* Pdf-IText Java Library
* Spring Data JPA as ORM with PostgreSQL  
* Integration source and deploy with CI/CD with CircleCI
* Deploy into Heroku
* Dockerfile
* Docker-compose

## DB Integration Tests with Spring Boot and Testcontainers

How to use Testcontainers for integration testing with Spring Data JPA and the PostgreSQL database.

## Configure Spring Actuator
Actuator trae funciones listas para producción a nuestra aplicación.

**Monitorear nuestra aplicación, recopilar métricas, comprender el tráfico o el estado de nuestra base de datos se vuelve trivial con esta dependencia.**

```
http://localhost:8090/actuator/
```
Reponse
```
{
  "_links": {
    "self": {
      "href": "http://localhost:8090/actuator",
      "templated": false
    },
    "health-path": {
      "href": "http://localhost:8090/actuator/health/{*path}",
      "templated": true
    },
    "health": {
      "href": "http://localhost:8090/actuator/health",
      "templated": false
    },
    "info": {
      "href": "http://localhost:8090/actuator/info",
      "templated": false
    }
  }
}
```