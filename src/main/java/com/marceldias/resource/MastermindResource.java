package com.marceldias.resource;

import com.marceldias.model.Color;
import com.marceldias.model.Game;
import com.marceldias.model.Guess;
import com.marceldias.model.User;
import com.marceldias.repository.GameRepository;
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

    @Autowired
    GameRepository gameRepository;

    @POST
    @Path("/new-game")
    public Game newGame(User user) {
        Game game = new Game(user);
        gameRepository.insert(game);
        return game;
    }

    @POST
    @Path("/guess")
    public Game guess(Guess guess) {

        if (guess.getGameKey() == null || guess.getGameKey().isEmpty()) {
            throw new IllegalArgumentException("The gameKey is null or empty!");
        }

        Game game = gameRepository.findByKey(guess.getGameKey());
        if (game.isExpired()) {
            throw new IllegalArgumentException("The game with gameKey " + guess.getGameKey() +" has expired! more than 5 minutes playing.");
        }

        return game;
    }

    @GET
    @Path("/games")
    public List<Game> getGames() {
        return gameRepository.findAll();
    }
}
