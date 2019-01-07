FROM node:11.6.0

RUN mkdir -p /opt/DistributedSystemsLabor
WORKDIR /opt/DistributedSystemsLabor

RUN npm install -g @angular/cli@7.1.4

COPY AngularClient/ /opt/DistributedSystemsLabor

CMD ng serve --host 0.0.0.0 --port 4200