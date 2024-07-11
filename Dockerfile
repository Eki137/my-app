FROM maven:3.8.1-openjdk-11
WORKDIR /app
COPY ..
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app.jar"]
