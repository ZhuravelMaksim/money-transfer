FROM adoptopenjdk/openjdk:17-alpine

VOLUME /tmp

RUN apk --no-cache add curl

ADD target/*.jar money-transfer-service.jar

EXPOSE 8080

CMD java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /assist-service.jar
