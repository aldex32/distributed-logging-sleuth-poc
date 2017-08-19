FROM java:8
VOLUME /tmp
ADD target/service-1-1.0-SNAPSHOT.jar service-1.jar
ENTRYPOINT ["java","-jar","/service-1.jar"]