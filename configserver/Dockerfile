#Start with a base image containing Java runtime
FROM openjdk:23-jdk-slim

# MAINTAINER instruction is deprecated in favor of using label
# MAINTAINER caogiahuy77@yahoo.com
# Information around who maintains the image
LABEL "org.opencontainers.image.authors"="huycao"

# Install curl
RUN apt-get update && \
    apt-get install -y curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Add the application's jar to the image
COPY target/configserver-0.0.1-SNAPSHOT.jar configserver-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "configserver-0.0.1-SNAPSHOT.jar"]
