FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/SWEurekabank-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8084

ENTRYPOINT ["java","-jar","/app/app.jar"]