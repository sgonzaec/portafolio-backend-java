FROM openjdk:17-jdk

WORKDIR /app

COPY out/artifacts/portfolio_backend_jar/portfolio-backend.jar /app/portfolio-backend.jar

RUN ls /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "portafolio-backend.jar"]