FROM openjdk:11
EXPOSE 8761
ADD discovery-service/target/discovery-service.jar discovery-service/discovery-service.jar
ENTRYPOINT ["java", "-jar", "discovery-service/discovery-service.jar"]