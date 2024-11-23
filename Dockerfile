# build
FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# execução
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/todo.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
