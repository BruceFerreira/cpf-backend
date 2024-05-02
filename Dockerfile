FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
VOLUME /tmp
COPY target/*.jar cpf.jar
COPY src/main/resources/application.properties /app/application.properties
ENTRYPOINT ["java","-jar","/app/cpf.jar"]
