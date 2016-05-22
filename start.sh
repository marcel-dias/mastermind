#!/usr/bin/env bash
if [ "x$MONGODB_URI" = "x" ]; then
    MONGODB_URI="mongodb://192.168.99.100:27017/mastermind"
fi
if [ "x$SERVER_PORT" = "x" ]; then
    SERVER_PORT="8080"
fi
if [ "x$JAR_PATH" = "x" ]; then
    JAR_PATH="target/mastermind.jar"
fi

java -jar $JAR_PATH --server.port=$SERVER_PORT --spring.data.mongodb.uri=$MONGODB_URI
