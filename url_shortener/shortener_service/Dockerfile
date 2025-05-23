FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/shortener_service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082

# Run the JAR file
ENTRYPOINT ["java","-jar","app.jar"]