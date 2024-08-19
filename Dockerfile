FROM openjdk:17-jdk-alpine

COPY target/your-app.jar /usr/app/your-app.jar

WORKDIR /usr/app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "your-app.jar"]