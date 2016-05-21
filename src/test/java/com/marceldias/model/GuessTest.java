package com.marceldias.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GuessTest {

    private final Integer GAME_SIZE = 8;

    @Test
    public void testCreateNewGuess() {
        Guess guess = new Guess();
        Assert.assertNotNull(guess.getCreatedAt());
    }

    @Test
    public void testCreateNewGuessWithCode() {
        String code = RandomStringUtils.random(GAME_SIZE, Color.COLORS);
        Guess guess = new Guess(code);

        Assert.assertNotNull(guess.getCreatedAt());
        Assert.assertNotNull(guess.getColors());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNewGuessWithNullCode() {
        new Guess(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNewGuessWithEmptyCode() {
        new Guess("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNewGuessWithWrongCode() {
        String code = "ABCDEFGH";
        Guess guess = new Guess(code);
        guess.populateColors(code);
    }

    @Test
    public void testGuessPopulateColors() {
        String code = RandomStringUtils.random(GAME_SIZE, Color.COLORS);
        Guess guess = new Guess(code);
        List<Color> colors = guess.populateColors(code);

        Assert.assertNotNull(guess.getColors());
        Assert.assertThat(guess.getColors(), IsEqual.equalTo(colors));
    }
}
