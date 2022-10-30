ARG gradleVersion=7.2.0-jdk17-alpine
# ARG gradleVersion=5.6.2-jdk11

## Stage 1 gradle build
FROM gradle:${gradleVersion} AS build

WORKDIR /app
COPY . .
RUN gradle build -x test

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /app /app
WORKDIR /app

EXPOSE 8080

# Non Root User Configuration
RUN groupadd -r -g 10001 appGrp \
    && useradd -r -u 10000 -s /sbin/nologin -d /opt/app/ -G appGrp app

USER 10000

COPY --from=build /app/build/libs/document-api-rest-1.0.jar /opt/app/app.jar
COPY --from=build /app/src/main/resources/templates /opt/templates

ENTRYPOINT ["java","-jar","/opt/app/app.jar"]