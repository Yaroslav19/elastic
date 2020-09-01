FROM openjdk:11-jre-slim
WORKDIR /usr/src/app
ADD target/*.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "app.jar"]