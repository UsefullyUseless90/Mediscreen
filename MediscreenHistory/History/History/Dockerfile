
FROM eclipse-temurin:17-jdk-jammy
#Listen port 8082
EXPOSE 8082
#Copy the app files from host to image the file system
COPY /build /build
#Set the directory for executing commands
WORKDIR java/com/mediscreen/History/HistoryApplication.java

CMD java -jar ./mediscreenHistory-0.0.1-SNAPSHOT.jar