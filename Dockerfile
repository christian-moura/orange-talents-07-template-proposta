FROM openjdk:11
# MAINTAINER Christian Rodrigues 
RUN  addgroup --gid 1000 spring || true
RUN  useradd -m -g spring --shell /bin/sh spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} propostas.jar
ENTRYPOINT ["java","-jar","/propostas.jar"] 
EXPOSE 8585
