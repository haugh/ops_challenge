#Here we are specifying the node image that we are gonna use and securing it by using the sha
ARG MAVEN_DIGEST=sha256:09c4bf58d7e7e31bdc1ab73ef10349b3a47c9a814e715e409034fb3293253ce2

FROM maven@${MAVEN_DIGEST} as builder 
WORKDIR /app

COPY src /app/src
COPY target /app/target
COPY pom.xml /app/

RUN mvn dependency:resolve
RUN mvn verify
RUN mvn package

FROM maven@${MAVEN_DIGEST} as java
WORKDIR /app
COPY --from=builder /app/target/worker-jar-with-dependencies.jar /app/
ENTRYPOINT exec java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -jar worker-jar-with-dependencies.jar