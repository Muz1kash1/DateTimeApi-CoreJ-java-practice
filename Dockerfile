
FROM openjdk:17-alpine
COPY /target/my-app-1.0-SNAPSHOT.jar /home/my-app-1.0-SNAPSHOT.jar
WORKDIR /target/my-app-1.0-SNAPSHOT.jar
CMD ["java","-jar","/home/my-app-1.0-SNAPSHOT.jar"]



