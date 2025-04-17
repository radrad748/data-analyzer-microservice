FROM maven:3.8.5-openjdk-17 AS build
#COPY /src /src
#COPY pom.xml /
WORKDIR /app
COPY pom.xml .
COPY src ./src
#RUN mvn -f /pom.xml clean package
RUN mvn clean package -X

FROM openjdk:17-jdk-slim
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]