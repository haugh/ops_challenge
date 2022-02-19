#Here we are specifying the node image that we are gonna use and securing it by using the sha
ARG OPENJDK_DIGEST=sha256:85f9c3c1358dcf178770c5f187d8844798ca23254d80c61c7bddfc84d4e2a011

FROM adoptopenjdk/maven-openjdk8@${OPENJDK_DIGEST} as maven

WORKDIR /app

COPY src /app/src
COPY target /app/target
COPY pom.xml /app/

RUN mvn dependency:resolve
RUN mvn verify
RUN mvn package

ENTRYPOINT exec java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -jar worker-jar-with-dependencies.jar