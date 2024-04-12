FROM openjdk:17.0.1-jdk-slim
ARG JAR_FILE=target/projeto-integracao-pix.jar
COPY ./target/projeto-integracao-pix.jar app.jar
EXPOSE 1234
ENTRYPOINT ["java", "-jar", "/app.jar", "localhost"]