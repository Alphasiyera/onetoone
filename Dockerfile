From openjdk:11
copy target/onetoone-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","mapping.jar"]