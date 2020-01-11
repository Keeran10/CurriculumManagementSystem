FROM node:10.16.3-alpine

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY package.json /usr/src/app/package.json
RUN npm install
RUN npm install -g @angular/cli 

COPY . /usr/src/app
EXPOSE 4200 49153
CMD npm run start