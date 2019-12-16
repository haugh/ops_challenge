## Voting Results App

This app is written in Node (w/ Angular UI) and displays the voting results transformed by the worker. This application requires the following env vars to run:

~~~
# The hostname of the running Postgres db
POSTGRES_HOST=

# The user to connect to the db with,
POSTGRES_USER=

# (Optional) The port the server listens on. Defaults to 4000
PORT=
~~~

This app was tested with **Node 10**. To build this app run the following:

~~~
# it is recommended to run using nodemon
npm install -g nodemon


# To build
npm ci && npm cache clean --force
~~~


To run:

~~~
nodemon server.js
~~~
