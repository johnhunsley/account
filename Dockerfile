FROM openjdk:8
VOLUME /tmp
ADD target/spring-account-example-*.jar /app.jar
RUN bash -c 'touch /app.jar'
#set the 'service' profile because this should always run as a service unless used as a dependency
ENV JAVA_OPTS="-Xmx256m --Dspring.profiles.active=service,prepopulated"
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]