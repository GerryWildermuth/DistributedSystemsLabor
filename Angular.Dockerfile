FROM node:10.15.0

RUN mkdir -p /opt/DistributedSystemsLabor
WORKDIR /opt/DistributedSystemsLabor

RUN npm install -g @angular/cli@6.4.1

COPY AngularClient/ /opt/DistributedSystemsLabor

CMD ng serve --host 0.0.0.0 --port 4200