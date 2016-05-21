#!/usr/bin/env bash
export MONGODB_URI="mongodb://192.168.99.100:27017/mastermind"
java -jar target/mastermind-0.0.1-SNAPSHOT.jar --server.port=8080 --spring.data.mongodb.uri=$MONGODB_URI
