## Worker App

This app is written in Java consumes vote data from Redis and stores them in a Postgres DB. This application requires the following env vars to run:

~~~
# The hostname of the running Redis db
REDIS_HOST=

# The hostname of the running Postgres db
POSTGRES_HOST=

# The user to connect to the db with,
POSTGRES_USER=
~~~

This app is tested in **Java 8**. To build with dependencies dependencies run the following:

~~~
mvn dependency:resolve
mvn verify
mvn package

# This will build a jar named `worker-jar-with-dependencies` in the `target` folder.
~~~

To run this app, issue the following command:

~~~
java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -jar worker-jar-with-dependencies.jar
~~~
