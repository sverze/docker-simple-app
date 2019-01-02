FROM anapsix/alpine-java
MAINTAINER sverze
COPY target/jersey-service-1.1.0-SNAPSHOT-jar-with-dependencies.jar /home/jersey-service.jar
CMD ["java","-jar","/home/jersey-service.jar"]
