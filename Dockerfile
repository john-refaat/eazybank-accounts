FROM eclipse-temurin:21-jdk

# Add the application's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="johnrstefanos"

# Ececulte the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]