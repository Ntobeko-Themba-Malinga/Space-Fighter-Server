FROM ubuntu:latest

RUN apt-get update
RUN apt-get install -y openjdk-18-jre
RUN apt-get install -y build-essential
RUN apt-get install -y make

ADD Space_Fighter_Server-1.0-SNAPSHOT-jar-with-dependencies.jar /srv/space_fighter.jar

WORKDIR /srv
EXPOSE 5000

CMD ["java", "-jar", "space_fighter.jar"]

