FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/*.jar /app/snowman-labs.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "snowman-labs.jar"]