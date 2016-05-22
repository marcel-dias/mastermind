FROM marceldiass/java-jre-server-8

MAINTAINER Marcel Dias<marceldiass@gmail.com>

ENV MONGODB_URI "mongodb://localhost:27017/mastermind"
ENV SERVER_PORT 8080
ENV JAR_PATH mastermind.jar

RUN mkdir /mastermind
COPY target/mastermind.jar /mastermind/mastermind.jar
COPY start.sh /mastermind/

EXPOSE $SERVER_PORT

WORKDIR /mastermind
CMD ["./start.sh"]

