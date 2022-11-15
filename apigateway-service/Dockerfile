FROM openjdk:11
EXPOSE 8900
ADD apigateway-service/target/apigateway-service.jar apigateway-service/apigateway-service.jar
ENTRYPOINT ["java", "-jar", "apigateway-service/apigateway-service.jar"]