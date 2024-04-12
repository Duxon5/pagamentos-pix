FROM openjdk:17.0.1-jdk-slim
ARG JAR_FILE=target/projeto-pix.jar
COPY ./target/projeto-pix.jar app.jar
EXPOSE 4321
ENTRYPOINT ["java", "-jar", "/app.jar"]