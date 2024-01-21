FROM openjdk:17
WORKDIR /opt
COPY ${JAR_FILE} /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar