FROM marceldiass/java-jre-server-8

MAINTAINER Marcel Dias<marceldiass@gmail.com>

LABEL org.label-schema.name="Mastermind game backend API" \
      org.label-schema.description="Mastermind game backend API but for this project we will be using 8 colors and 8 positions." \
      org.label-schema.vcs-url="https://github.com/marceldiass/mastermind" \
      org.label-schema.schema-version="1.0"

ENV MONGODB_URI "mongodb://localhost:27017/mastermind"
ENV SERVER_PORT 8080
ENV JAR_PATH mastermind.jar

RUN mkdir /mastermind
COPY target/mastermind.jar /mastermind/mastermind.jar
COPY start.sh /mastermind/

EXPOSE $SERVER_PORT

WORKDIR /mastermind
CMD ["./start.sh"]
