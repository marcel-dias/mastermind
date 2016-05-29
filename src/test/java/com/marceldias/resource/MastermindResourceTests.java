package com.marceldias.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.internal.mapping.Jackson2Mapper;
import com.jayway.restassured.mapper.ObjectMapper;
import com.jayway.restassured.mapper.factory.Jackson2ObjectMapperFactory;
import com.marceldias.MastermindApplication;
import com.marceldias.json.factory.GameJsonFactory;
import com.marceldias.model.*;
import com.marceldias.repository.GameRepository;
import com.marceldias.service.GameService;
import com.marceldias.service.GuessService;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsArray;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.*;
import org.hamcrest.text.IsEmptyString;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MastermindApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class MastermindResourceTests {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;

    @Autowired
    private GuessService guessService;

    @Value("${local.server.port}")
    private int port;

    private Game game;

    private User user;

    @Before
    public void setUp() {

        user = new User("test");
        game = new Game(user);

        gameRepository.deleteAll();
        gameRepository.save(game);

        RestAssured.port = port;
    }

    @Test
    public void testGetGames() {
        Jackson2ObjectMapperFactory factory = new GameJsonFactory();
        ObjectMapper mapper = new Jackson2Mapper(factory);

        Game[] gameList = RestAssured.when()
                .get("/games").as(Game[].class, mapper);

        Assert.assertThat(gameList.length, IsEqual.equalTo(1));
        Assert.assertThat(gameList[0].getKey(), IsEqual.equalTo(game.getKey()));
    }

    @Test
    public void testCreateNewGame() {
        Jackson2ObjectMapperFactory factory = new GameJsonFactory();
        ObjectMapper mapper = new Jackson2Mapper(factory);

        Game newGame = RestAssured
            .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(user)
            .when()
                .post("/new-game").as(Game.class, mapper);

        Assert.assertThat(newGame.getKey(), IsNull.notNullValue());
        Assert.assertThat(newGame.getUser().getName(), IsEqual.equalTo(user.getName()));
        Assert.assertThat(newGame.getCodeLength(), IsEqual.equalTo(8));
        Assert.assertThat(newGame.getColors(), IsEqual.equalTo(Color.values()));
        Assert.assertThat(newGame.isSolved(), IsEqual.equalTo(Boolean.FALSE));
    }

    @Test
    public void testCreateGame() {
        RestAssured
            .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(user)
            .when()
                .post("/new-game")
            //Assertions
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("key", IsNull.notNullValue())
                .body("solved", IsEqual.equalTo(Boolean.FALSE))
                .body("expired", IsEqual.equalTo(Boolean.FALSE))
                .body("user.name", IsEqual.equalTo("test"))
                .body("codeLength", IsEqual.equalTo(8))
                .body("guessesCount", IsEqual.equalTo(0))
                .body("guesses", IsEmptyCollection.empty());
    }

    @Test
    public void testGuess() throws JsonProcessingException {

        Guess guess = new Guess();
        guess.setGameKey(game.getKey());
        guess.setCode(Color.generateColorCode(8));

        GuessResult result = guessService.processGuess(guess, game);
        RestAssured
            .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(guess)
            .when()
                .post("/guess")
            //Assertions
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("key", IsNull.notNullValue())
                .body("solved", IsEqual.equalTo(Boolean.FALSE))
                .body("expired", IsEqual.equalTo(Boolean.FALSE))
                .body("user.name", IsEqual.equalTo("test"))
                .body("codeLength", IsEqual.equalTo(8))
                .body("guessesCount", IsEqual.equalTo(1))
                .body("result.exact", IsEqual.equalTo(result.getExact()))
                .body("result.near", IsEqual.equalTo(result.getNear()))
                .body("guesses", IsCollectionWithSize.hasSize(1))
                .body("guesses[0].exact", Is.is(result.getExact()))
                .body("guesses[0].near", Is.is(result.getNear()));
    }

    @Test
    public void testGuessWithoutCode() throws JsonProcessingException {

        Guess guess = new Guess();
        guess.setGameKey(game.getKey());

        RestAssured
            .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(guess)
            .when()
                .post("/guess")
            //Assertions
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", Matchers.not(IsEmptyString.isEmptyString()));
    }

    @Test
    public void testGuessWithoutGamekey() throws JsonProcessingException {

        Guess guess = new Guess();
        guess.setCode(Color.generateColorCode(8));
        RestAssured
            .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(guess)
            .when()
                .post("/guess")
            //Assertions
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", Matchers.not(IsEmptyString.isEmptyString()));
    }

    @Test
    public void testClearGameList() {

        Jackson2ObjectMapperFactory factory = new GameJsonFactory();
        ObjectMapper mapper = new Jackson2Mapper(factory);

        Game[] gameList = RestAssured.when()
                .get("/games").as(Game[].class, mapper);

        Assert.assertThat(gameList.length, IsEqual.equalTo(1));
        Assert.assertThat(gameList[0].getKey(), IsEqual.equalTo(game.getKey()));

        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
            .when()
                .post("/clear")
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("message", Matchers.not(IsEmptyString.isEmptyString()));

        gameList = RestAssured.when()
                .get("/games").as(Game[].class, mapper);

        Assert.assertThat(gameList.length, IsEqual.equalTo(0));
    }
}
