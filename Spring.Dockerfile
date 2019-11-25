FROM openjdk:8-alpine
RUN mkdir -p /opt/DistributedSystemsLabor
WORKDIR /opt/DistributedSystemsLabor
COPY target/DistributedSystemsLabor-0.0.5.jar /opt/DistributedSystemsLabor
CMD ["java", "-jar", "DistributedSystemsLabor-0.0.5.jar"]
EXPOSE 8080