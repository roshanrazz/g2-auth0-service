FROM openjdk:17

EXPOSE 9009

ADD /target/*.jar g2-auth0-service.jar

ENTRYPOINT [ "java","-jar","/g2-auth0-service.jar"]