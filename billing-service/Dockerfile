FROM openjdk:11
EXPOSE 9002
ADD billing-service/target/billing-service.jar billing-service/billing-service.jar
ENTRYPOINT ["java", "-jar", "billing-service/billing-service.jar"]