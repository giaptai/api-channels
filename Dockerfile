#FROM openjdk:20
#MAINTAINER iat.com
#VOLUME /tmp
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} momentsqa-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java", "-jar", "momentsqa-0.0.1-SNAPSHOT.jar"]

FROM openjdk:20
#VOLUME /tmp
EXPOSE 8055
COPY target/*.jar momentsqa-0.0.1-SNAPSHOT.jar
# When container is running, those commands below here will execute
ENTRYPOINT ["java", "-jar", "/momentsqa-0.0.1-SNAPSHOT.jar"]
#ENTRYPOINT ["java", "-jar", "/momentsqa-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=dev"]
