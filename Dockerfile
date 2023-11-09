FROM openjdk:8

COPY target/spring-boot-telegram-0.0.1-SNAPSHOT.jar server.jar

ENTRYPOINT ["java", "-server", "-jar", "/server.jar","--spring.profiles.active=test"]
