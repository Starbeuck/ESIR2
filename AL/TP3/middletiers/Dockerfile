FROM ubuntu:14.04
RUN apt-get update && apt-get install -y openjdk-7-jdk git libgradle-core-java
RUN git clone https://github.com/Netflix/recipes-rss.git

WORKDIR /recipes-rss
RUN ./gradlew clean build -x javadoc

RUN export APP_ENV=dev

CMD java -Deureka.serviceUrl.default=http://tomcat/eureka/v2/ -jar rss-middletier/build/libs/rss-middletier-*SNAPSHOT.jar
