FROM eclipse-temurin:17-jdk-jammy
#Listen port 8081
EXPOSE 8081
#Copy the app files from host to image the file system
COPY /build /build
#Set the directory for executing commands
WORKDIR java/com/MediScreen/MediScreen/MediScreenApplication.java

CMD java -jar ./mediscreenPatients-0.0.1-SNAPSHOT.jar