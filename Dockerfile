FROM openjdk:17

WORKDIR /app

COPY ./techblog-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java","-jar","-Dserver.port=8081","techblog-0.0.1-SNAPSHOT.jar"]