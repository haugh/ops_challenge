#Here we are specifying the node image that we are gonna use and securing it by using the sha
ARG OPENJDK_DIGEST=sha256:f53b9dfe3a1da1a7df2a9158df1932689ce24fc001da1a5e8e6b48e10cc3f75b

FROM maven@${OPENJDK_DIGEST} as OPENJDK
WORKDIR /app

COPY src /app/src
COPY target /app/target
COPY pom.xml /app/

RUN mvn dependency:resolve
RUN mvn verify
RUN mvn package

ENTRYPOINT exec java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -jar worker-jar-with-dependencies.jar