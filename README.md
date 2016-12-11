# Mastermind API

[![CircleCI](https://circleci.com/gh/marceldiass/mastermind.svg?style=svg)](https://circleci.com/gh/marceldiass/mastermind)
[![codecov](https://codecov.io/gh/marceldiass/mastermind/branch/master/graph/badge.svg)](https://codecov.io/gh/marceldiass/mastermind)
[![size](https://images.microbadger.com/badges/image/marceldiass/mastermind.svg)](https://microbadger.com/images/marceldiass/mastermind)
[![version](https://images.microbadger.com/badges/version/marceldiass/mastermind.svg)](https://microbadger.com/images/marceldiass/mastermind)

Let's play Mastermind! If you've never played Mastermind, take a few minutes to check out the rules here: [Mastermind](https://en.wikipedia.org/wiki/Mastermind_(board_game)#Gameplay_and_rules).

The typical Mastermind game is played with 4 colors and 4 positions; however, for this project we will be using 8 colors and 8 positions.

## Dependencies
* Java JDK8
* Maven 3+
* MongoDB

## How to build and run

To build the sources you need to you maven.  
```bash
mvn clean install
```
If you like to use docker, you can build a Docker image as well  
```bash
docker build -t marceldiass/mastermind .
```  
Before run the container you need to setup a MongoDB server. To use Docker for that just run:  
```bash
docker run -d --name mastermind-mongo mongo
```  
And finally run it  
```bash
docker run -d --name mastermind --link mastermind-mongo -p 8080:8080 -e MONGODB_URI="mongodb://mastermind-mongo:27017/mastermind" marceldiass/mastermind
```

Or you can use docker-compose
```
docker-compose up
```  

## How to consume

As it is just an API you can you curl or any other HTTP client to reach the endpoints.  
Below we have request examples:

* Create a new game  
```bash
curl -i -X POST -H "Content-Type: application/json" -d '{"name":"Marcel Dias"}' "http://localhost:8080/new-game"
```
New Game response body
```json
{
 "key":"57421c60bee8d9ae4e7fcca6",
 "colors":["R","B","G","Y","O","P","C","M"],
 "codeLength":8,
 "user":{"name":"Marcel Dias"},
 "solved":false,
 "guesses":[],
 "result":null,
 "expired":false,
 "guessesCount":0
}
```
* Try a guess  
```bash
curl -i -X POST -H "Content-Type: application/json" -d '{ "gameKey":"5741c740e4b00960f2e616bd", "code": ["Y","R","G","R","Y","B","C","Y"] }' "http://localhost:8080/guess"
```  
Guess response body
```json
{
 "key":"57421c60bee8d9ae4e7fcca6",
 "colors":["R","B","G","Y","O","P","C","M"],
 "codeLength":8,
 "user":{"name":"Marcel Dias"},
 "solved":false,
 "guesses":[{"guess":["Y","R","G","R","Y","B","C","Y"],"exact":0,"near":2}],
 "result":{"guess":["Y","R","G","R","Y","B","C","Y"],"exact":0,"near":2},
 "expired":false,
 "guessesCount":1
}
```

## Tech stack

### Spring Boot with jersey
[Spring Boot](http://projects.spring.io/spring-boot/) is an awesome framework to quickly build an Rest based Application. It have dozen of prebuilt integrations.  
Jersey because it is a Java standard and I think it is easier to understand the endpoints code.  

### MongoDB as data store
I've chosen MongoDB because:
* I don't store relational data
* I can easy get all the game data
* it is really easy to use with spring
* it has an active community
* it has prebuilt integration with Spring-boot
