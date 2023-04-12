FROM openjdk:8u131-jdk-alpine
MAINTAINER Rohn "my@gmail.com"
EXPOSE 8080
WORKDIR /usr/local/bin
COPY maven/services-1.0.1-SNAPSHOT.jar mydockerapp.jar
CMD ["java","-jar","mydockerapp.jar"]