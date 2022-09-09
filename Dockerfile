FROM openjdk:18-alpine
EXPOSE 8080
ADD target/vital-sign-system.jar vital-sign-system.jar
ENTRYPOINT ["java","-jar","/vital-sign-system.jar"]