FROM openjdk:latest
COPY build/libs/hackaton.jar .
CMD ["java", "-jar", "hackaton.jar", "--spring.profiles.active=dev"]
EXPOSE 8080
