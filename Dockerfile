FROM openjdk:17-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

ARG JAR_FILE=build/libs/*jar
COPY ${JAR_FILE} ibeer-app.jar

ENTRYPOINT ["java","-Xms128M","-Xmx128M","-XX:MetaspaceSize=64m","-XX:MaxMetaspaceSize=128m","-jar","/ibeer-app.jar"]