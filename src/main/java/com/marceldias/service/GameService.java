package com.marceldias.service;

import com.marceldias.model.Game;
import com.marceldias.model.Guess;
import com.marceldias.model.GuessResult;

import java.util.List;

/**
 * The GameService interface defines all public business behaviors for
 * Game operations.
 */
public interface GameService {

    /**
     * Persists a Game in the data store.
     * @param game A Game object to be persisted.
     * @return A persisted Game object or <code>null</code> if a problem occurred.
     */
    Game create(Game game);

    /**
     * Updates a previously persisted Game in the data store.
     * @param game A Game object to be updated.
     * @param result A game guess result
     * @return An updated Game object or <code>null</code> if a problem occurred.
     */
    Game update(Game game, GuessResult result);

    /**
     * Find all games
     * @return A Collection of Game objects.
     */
    List<Game> findAll();

    /**
     * Find a single Game by key identifier.
     * @param key A Game key string
     * @return A Game object to the given key or <code>null</code> if there is no Game with that key
     */
    Game findByKey(String key);

    /**
     * Delete all Games
     */
    void clear();

    /**
     * Process the guess to create a feedback to the user.
     * With number of exacts and nears and also the guess history
     *
     * @param guess A guess code to solve the game
     * @return A updated game with the guess result
     */
    Game processGuess(Guess guess);

}
