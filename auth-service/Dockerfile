FROM openjdk:11
EXPOSE 9001
ADD auth-service/target/auth-service.jar auth-service/auth-service.jar
ENTRYPOINT ["java", "-jar", "auth-service/auth-service.jar"]