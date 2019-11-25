# build static website
FROM node:alpine as builder
RUN apk update && apk add --no-cache make git

# Create app directory
WORKDIR /AngularClient

# Install app dependencies
COPY package.json package-lock.json /AngularClient/
#COPY AngularClient/ /opt/DistributedSystemsLabor #old

RUN cd /AngularClient && npm set progress=false && npm install
#-g @angular/cli@6.2.8
# Copy project files into the docker image
COPY .  /AngularClient

RUN cd /AngularClient && npm run build

# build a small nginx image with static website
FROM nginx:alpine
## Remove default nginx website
RUN rm -rf /usr/share/nginx/html/*
## From 'builder' copy website to default nginx public folder
COPY --from=builder /AngularClient/dist /usr/share/nginx/html
EXPOSE 4200
CMD ["nginx", "-g", "daemon off;"]
