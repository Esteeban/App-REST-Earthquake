FROM openjdk:11-jdkalpine
EXPOSE 8080
ARG WAR_FILE=target/cpyd-0.0.1-SNAPSHOT.war
ADD ${WAR_FILE} cpyd-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/cpyd-0.0.1-SNAPSHOT.war"]

