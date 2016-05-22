package com.marceldias.service;

import com.marceldias.model.*;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class GuessServiceTest {

    private GuessService guessService;
    private Game game;
    private User user;
    private List<Color> code;
    private Guess guess;

    @Before
    public void setUp() {
        guessService = new GuessServiceBean();
        user = new User("Test "+System.currentTimeMillis());
        code = Color.generateColorCode(8);
        game = new Game(user);
        guess = new Guess();
    }

    @Test
    public void testWinnerGuess() {
        game = Mockito.spy(game);
        Mockito.when(game.getCode()).thenReturn(code);

        guess.setCode(code);
        GuessResult result = guessService.processGuess(guess, game);

        Assert.assertThat(result.getGuess(), IsEqual.equalTo(guess.getCode()));
        Assert.assertThat(result.getExact(), IsEqual.equalTo(8));
        Assert.assertThat(result.getNear(), IsEqual.equalTo(0));
    }

    @Test
    public void testOneExactSevenNearGuess() {
        code = Arrays.asList(Color.B, Color.C, Color.G, Color.C, Color.G, Color.M, Color.G, Color.Y);
        game = Mockito.spy(game);
        Mockito.when(game.getCode()).thenReturn(code);

        List<Color> guessedCode = Arrays.asList(Color.B, Color.B, Color.B, Color.B, Color.B, Color.B, Color.B, Color.B);
        guess.setCode(guessedCode);
        GuessResult result = guessService.processGuess(guess, game);

        Assert.assertThat(result.getGuess(), IsEqual.equalTo(guess.getCode()));
        Assert.assertThat(result.getExact(), IsEqual.equalTo(1));
        Assert.assertThat(result.getNear(), IsEqual.equalTo(7));
    }

    @Test
    public void testFourExactZeroNearGuess() {
        code = Arrays.asList(Color.B, Color.C, Color.R, Color.C, Color.G, Color.M, Color.G, Color.Y);
        game = Mockito.spy(game);
        Mockito.when(game.getCode()).thenReturn(code);

        List<Color> guessedCode = Arrays.asList(Color.B, Color.O, Color.R, Color.C, Color.P, Color.O, Color.P, Color.Y);
        guess.setCode(guessedCode);
        GuessResult result = guessService.processGuess(guess, game);

        Assert.assertThat(result.getGuess(), IsEqual.equalTo(guess.getCode()));
        Assert.assertThat(result.getExact(), IsEqual.equalTo(4));
        Assert.assertThat(result.getNear(), IsEqual.equalTo(0));
    }
}
