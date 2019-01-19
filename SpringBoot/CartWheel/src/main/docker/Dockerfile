FROM java:8
VOLUME /tmp
ADD /home/mohu/projects/githubrepos/SpringSamples/SpringBoot/CartWheel/target/CartWheel-1.0-SNAPSHOT.jar CartWheel.jar
RUN sh -c 'touch /CartWheel.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/CartWheel.jar"]
EXPOSE 8190