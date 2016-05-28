package com.marceldias.resource;

import com.marceldias.model.*;
import com.marceldias.service.GameService;
import com.marceldias.service.GuessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MastermindResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MastermindResource.class);

    @Autowired
    GameService gameService;

    @Autowired
    GuessService guessService;

    @POST
    @Path("/clean")
    public ResponseMessage clean() {
        gameService.clear();
        ResponseMessage msg = new ResponseMessage("Cleared All Games!");
        return msg;
    }

    @POST
    @Path("/new-game")
    public Game newGame(User user) {
        Game game = new Game(user);
        gameService.create(game);
        return game;
    }

    @POST
    @Path("/guess")
    public Game guess(Guess guess) {
        LOGGER.info(guess.toString());
        Game game = gameService.processGuess(guess);
        return game;
    }

    @GET
    @Path("/games")
    public List<Game> getGames() {
        return gameService.findAll();
    }
}
