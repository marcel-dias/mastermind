package com.marceldias.service;

import com.marceldias.model.Game;
import com.marceldias.model.Guess;
import com.marceldias.model.GuessResult;
import com.marceldias.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceBean implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GuessService guessService;

    @Override
    public Game create(Game game) {
        return gameRepository.insert(game);
    }

    @Override
    public Game update(Game game, GuessResult result) {
        game.setResult(result);
        if (result.getExact() == 8) {
            game.setSolved( Boolean.TRUE);
        }
        return gameRepository.save(game);
    }

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game findByKey(String key) {
        return gameRepository.findByKey(key);
    }

    public void clear() {
        gameRepository.deleteAll();
    }

    @Override
    public Game processGuess(Guess guess) {
        if (guess.getGameKey() == null || guess.getGameKey().isEmpty()) {
            throw new IllegalArgumentException("The gameKey is null or empty!");
        }

        Game game = findByKey(guess.getGameKey());
        if (game.isExpired()) {
            throw new IllegalArgumentException("The game with gameKey " + guess.getGameKey() + " has expired! more than 5 minutes playing.");
        } else if (game.isSolved()) {
            return game;
        }

        GuessResult result = guessService.processGuess(guess, game);
        return update(game, result);
    }
}
