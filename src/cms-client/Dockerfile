FROM node:10.16.0

WORKDIR /app

COPY package.json /app/package.json
RUN npm install
RUN npm install -g @angular/cli@8.3.5

COPY . /app