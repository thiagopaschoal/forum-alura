#FROM adoptopenjdk/openjdk11
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx512m", "-jar", "./app.jar"]