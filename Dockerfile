#base image with jdk
From eclipse-temurin:21-jdk

#working dir inside container
WORKDIR /app

#copy jar file inside container
COPY target/Chatbot-0.0.1-SNAPSHOT.jar aiChatbot.jar

#expose port
EXPOSE 8080

#command to run app.jar
ENTRYPOINT ["java", "-jar", "aiChatbot.jar"]