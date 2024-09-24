# the base image
FROM eclipse-temurin:21

# the JAR file path
WORKDIR /opt

ENV PORT 8090
EXPOSE 8090

# Copy the JAR file from the build context into the Docker image
COPY target/*.jar /opt/madisgateway.jar  

CMD apt-get update -y

# Set the default command to run the Java application
ENTRYPOINT ["java", "-Xmx3076M", "-jar", "/madisgateway.jar"]