FROM maven:3.8.4 as build
WORKDIR workspace

COPY src src
COPY pom.xml .
RUN mvn package

FROM openjdk:17-alpine
COPY --from=build /workspace/target/bp-keeper-0.0.1-SNAPSHOT-jar-with-dependencies.jar bp-keeper.jar
ENTRYPOINT ["java","-jar","bp-keeper.jar"]
