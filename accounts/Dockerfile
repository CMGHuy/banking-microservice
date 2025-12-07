#Start with a base image containing Java runtime
FROM eclipse-temurin:23-jdk

# MAINTAINER instruction is deprecated in favor of using label
# MAINTAINER caogiahuy77@yahoo.com
# Information around who maintains the image
LABEL "org.opencontainers.image.authors"="huycao"

# Create folder
RUN mkdir -p /app/libs

# Add the application's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# Copy opentelemetry javaagent
COPY target/dependency/opentelemetry-javaagent-*.jar /app/libs/

# execute the application
ENTRYPOINT ["java", "-javaagent:/app/libs/opentelemetry-javaagent-2.22.0.jar", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]
