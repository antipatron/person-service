FROM openjdk:11
COPY "./build/libs/person-service-api.jar" "person-service.jar"
EXPOSE 7978
ENTRYPOINT ["java", "-jar", "person-service.jar"]