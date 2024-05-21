FROM openjdk:22
VOLUME /tmp
ADD target/nationalbank.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]