#Here we are specifying the node image that we are gonna use and securing it by using the sha
ARG OPENJDK_DIGEST=sha256:93227b5ffb978363dfa3d493a0d25ccc943405260865437660bfec2af3174a73
ARG MAVEN_DIGEST=sha256:cdb1efc3e8f115a65e8110467128f582c575b7e8c183d11429d155291ab310e3

FROM maven@${MAVEN_DIGEST} as builder 
WORKDIR /app

COPY src /app/src
COPY target /app/target
COPY pom.xml /app/

RUN mvn dependency:resolve
RUN mvn verify
RUN mvn package

FROM openjdk@${OPENJDK_DIGEST} as OPENJDK
WORKDIR /app
COPY --from=builder /app/target/worker-jar-with-dependencies.jar /app/
CMD [ "java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -jar worker-jar-with-dependencies.jar" ]