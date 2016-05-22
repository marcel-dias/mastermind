package com.marceldias.service;

import com.marceldias.model.Game;
import com.marceldias.model.Guess;
import com.marceldias.model.GuessResult;

/**
 * The GuessService interface defines all public business behaviors for
 * Guess operations.
 *
 */
public interface GuessService {

    /**
     * Process the code guess to get the exact and near sections
     * @param guess A player code guess
     * @param game The played game
     * @return A GuessResult with the number of exacts and nears
     */
    GuessResult processGuess(Guess guess, Game game);
}
