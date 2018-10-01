FROM docker.fme.de/docker/tomcat8-oracle-jdk:latest

COPY Config/* /opt/

RUN ["chmod", "+x", "/opt/restapi.jar"]
RUN ["chmod", "+x", "/opt/run.sh"]