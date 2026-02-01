# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-slim
WORKDIR /app
# Using a wildcard to catch the generated jar file
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Render provides a $PORT variable, but Spring Boot 
# will pick it up if we pass it as a system property
ENTRYPOINT ["java", "-Dserver.port=${PORT:8080}", "-jar", "app.jar"]
