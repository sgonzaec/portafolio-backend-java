FROM openjdk:17-jdk

WORKDIR /app

COPY out/artifacts/portfolio/portfolio-backend.jar /app/portfolio.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/portfolio.jar"]