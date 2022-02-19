#Here we are specifying the node image that we are gonna use and securing it by using the sha
ARG NODE_DIGEST=sha256:97a8441349e0810b2c438518a45c63cf6b3f798f60d97bcf50e452ae467bb706
ARG NGINX_DIGEST=sha256:050385609d832fae11b007fbbfba77d0bba12bf72bc0dca0ac03e09b1998580f

FROM node@${NODE_DIGEST} as nodejs

RUN apk update 
RUN apk add postgresql

WORKDIR /app

COPY server.js package.json package-lock.json /app/

RUN npm ci
RUN npm cache clean --force
RUN npm install -g nodemon

COPY tests /app/tests
COPY views /app/views

CMD ["nodemon", "server.js"]

FROM nginx@${NGINX_DIGEST} as nginx
COPY --from=nodejs /app /app
COPY default-nginx.conf /etc/nginx/conf.d/default.conf
CMD ["nginx", "-g", "daemon off;"]
#1