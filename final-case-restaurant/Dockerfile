# FROM openjdk:17-jdk-alpine
# COPY target/final-case-restaurant-0.0.1-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]

FROM maven:3.8.5-openjdk-17 AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package -DskipTests

FROM openjdk:17

EXPOSE 8080

#default command
CMD java -jar /data/final-case-restaurant-0.0.1-SNAPSHOT.jar

#copy hello world to docker image from builder image

COPY --from=maven_build /tmp/target/final-case-restaurant-0.0.1-SNAPSHOT.jar /data/final-case-restaurant-0.0.1-SNAPSHOT.jar
