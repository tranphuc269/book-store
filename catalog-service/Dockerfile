FROM openjdk:11
EXPOSE 9004
ADD catalog-service/target/catalog-service.jar catalog-service/catalog-service.jar
ENTRYPOINT ["java", "-jar", "catalog-service/catalog-service.jar"]