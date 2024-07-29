FROM amazoncorretto:11
COPY build/libs/*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]