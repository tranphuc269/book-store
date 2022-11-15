FROM openjdk:11
EXPOSE 9003
ADD order-service/target/order-service.jar order-service/order-service.jar
ENTRYPOINT ["java", "-jar", "order-service/order-service.jar"]