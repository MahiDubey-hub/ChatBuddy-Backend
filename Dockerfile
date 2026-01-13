#base image with jdk
From eclipse-temurin:21-jdk

#working dir inside container
WORKDIR /app

#copy jar file inside container
COPY target/*.jar app.jar

#expose port
EXPOSE 8080

#command to run app.jar
ENTRYPOINT ["java", "-jar", "aiChatbot.jar"]