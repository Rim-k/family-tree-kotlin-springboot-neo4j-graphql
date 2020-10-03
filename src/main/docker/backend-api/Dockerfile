#see https://hub.docker.com/_/openjdk/
FROM openjdk:8-jdk-alpine

ARG JAR_FILE=demo-*.jar

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS
ENV SLEEP_TIME=0
#ENV JAVA_OPTS="-Dsun.jnu.encoding=UTF-8 -Dfile.encoding=UTF-8"

ENV DB_USERNAME=""
ENV DB_PASSWORD=""
ENV DB_ENDPOINT=""
# Add a user to run our application so that it doesn't need to run as root
RUN adduser -D -s /bin/sh demo

WORKDIR /home/demo

USER demo

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080