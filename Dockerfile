FROM openjdk:latest
COPY build/libs/hackaton.jar .
CMD ["java", "-jar", "hackaton.jar", "--spring.profiles.active=docker"]
EXPOSE 8080
