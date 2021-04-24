FROM openjdk:11-jre-slim-buster
VOLUME /libs
EXPOSE 9090
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
CMD ["./gradlew", "bootJar"]
ADD build/libs/finance-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/app.jar"]
