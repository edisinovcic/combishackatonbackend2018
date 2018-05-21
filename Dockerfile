FROM openjdk:latest
COPY build/libs/hackaton.jar .
CMD ["java", "-jar", "hackaton.jar"]
EXPOSE 8080
