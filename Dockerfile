FROM openjdk:17-jdk-alpine

LABEL name="iBeer Service"
LABEL description="A simple RESTful API for cataloging beers"
LABEL maintainer="euiagopaixao@gmail.com"

RUN addgroup -S ibeer-group && adduser -S ibeer-user -G ibeer-group
USER ibeer-user:ibeer-group

ARG JAR_FILE=build/libs/*jar
COPY ${JAR_FILE} ibeer-service.jar

ENTRYPOINT ["java", "-Xms128m", "-Xmx256m", "-XX:MetaspaceSize=64m", "-XX:MaxMetaspaceSize=128m", "-jar", "ibeer-service.jar"]

EXPOSE 8081