# Mastermind API

Let's play Mastermind! If you've never played Mastermind, take a few minutes to check out the rules here: [Mastermind](https://en.wikipedia.org/wiki/Mastermind_(board_game)#Gameplay_and_rules).

The typical Mastermind game is played with 4 colors and 4 positions; however, for this project we will be using 8 colors and 8 positions.

## Dependencies
* Java JDK8
* Maven 3+
* MongoDB

## How to build and run

To build the sources you need to you maven.  
`mvn clean install`  
If you like to use docker, you can build a Docker image as well  
`docker build -t marceldiass/mastermind .`  
Before run the container you need to setup a MongoDB server. To use Docker for that just run:  
`docker run -d --name mastermind-mongo mongo`  
And finally run it  
`docker run -d --name mastermind --link mastermind-mongo -p 8080:8080 -e MONGODB_URI="mongodb://mastermind-mongo:27017/mastermind" marceldiass/mastermind`


## How to consume

As it is just an API you can you curl or any other HTTP client to reach the endpoints.  
Below we have request examples:

* Create a new game  
`curl -i -X POST -H "Content-Type: application/json" -d '{"name":"MarcelDias"}' "http://localhost:8080/new-game"`
* Try a guess  
`curl -i -X POST -H "Content-Type: application/json" -d '{ "gameKey":"5741c740e4b00960f2e616bd", "code": ["Y","R","G","R","Y","B","C","Y"] }' "http://localhost:8080/guess"`

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
