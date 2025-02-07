FROM maven:3.8.7-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/portfolio-backend.jar portfolio.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "/app/portfolio.jar", "--server.port=8000"]