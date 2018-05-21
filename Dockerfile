FROM openjdk:latest
COPY build/lib/hackaton.jar .
CMD ["java", "-jar", "hackaton.jar"]
EXPOSE 8080
