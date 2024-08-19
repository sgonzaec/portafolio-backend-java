FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY out/artifacts/portafolio-backend_jar/portafolio-backend.jar /app/portafolio-backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "portafolio-backend.jar"]