FROM openjdk:11
EXPOSE 8900
ADD payment-service/target/payment-service.jar payment-service/payment-service.jar
ENTRYPOINT ["java", "-jar", "payment-service/payment-service.jar"]