FROM openjdk:8-jdk-alpine
MAINTAINER experto.com
VOLUME /tmp
EXPOSE 8080
ARG jar_file=target/demo-0.0.1-SNAPSHOT.jar
ADD ${jar_file} springbootdocker.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/springbootdocker.jar"]