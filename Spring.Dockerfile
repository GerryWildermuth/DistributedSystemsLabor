FROM openjdk:8-alpine
RUN mkdir -p /opt/StudienProjektWS19
WORKDIR /opt/StudienProjektWS19
COPY target/StudienProjektWS19-0.0.5.jar /opt/StudienProjektWS19
CMD ["java", "-jar", "StudienProjektWS19-0.0.5.jar"]
EXPOSE 8080