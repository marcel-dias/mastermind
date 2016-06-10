package com.marceldias.service;

import com.marceldias.model.Color;
import com.marceldias.model.Game;
import com.marceldias.model.Guess;
import com.marceldias.model.GuessResult;
import org.springframework.stereotype.Service;

@Service
public class GuessServiceBean implements GuessService {

    @Override
    public GuessResult processGuess(Guess guess, Game game) {
        if (guess.getCode() == null || guess.getCode().isEmpty() ||
                guess.getCode().size() != game.getCodeLength()) {
            throw new IllegalArgumentException("The code guessed is null, empty or with the wrong size!");
        }
        GuessResult result = new GuessResult();
        result.setGuess(guess.getCode());

        if (guess.getCode().equals(game.getCode())) {
            result.setExact(8);
            result.setNear(0);
        } else {
            result = validateCodeSequence(game, result);
        }
        return result;
    }

    private GuessResult validateCodeSequence(Game game, GuessResult result) {
        int exact = 0;
        int near = 0;
        for (int i = 0; i < game.getCodeLength(); i++) {
            Color color = result.getGuess().get(i);
            Color codeColor = game.getCode().get(i);
            if (color == codeColor) {
                ++exact;
            } else if (game.getCode().contains(color)) {
                ++near;
            }
        }
        result.setExact(exact);
        result.setNear(near);
        return result ;
    }
}
