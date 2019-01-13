FROM node:latest

# Create app directory
WORKDIR /AngularClient

# Copy project files into the docker image
COPY .  /AngularClient

RUN cd /AngularClient && npm install -g @angular/cli@6.2.8

RUN cd /AngularClient && npm run build

CMD ng serve
