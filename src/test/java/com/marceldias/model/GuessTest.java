package com.marceldias.model;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GuessTest {

    @Test
    public void testNewGuessWithCode() {
        List<Color> code = Arrays.asList(Color.B, Color.C, Color.G, Color.C, Color.G, Color.M, Color.G, Color.Y);
        Guess guess = new Guess();
        guess.setCode(code);

        Assert.assertNotNull(guess.getCode());
        Assert.assertThat(guess.getCode(), IsEqual.equalTo(code));
    }

    @Test
    public void testNewGuessWithGameKey() {
        List<Color> code = Arrays.asList(Color.P, Color.C, Color.B, Color.R, Color.O, Color.M, Color.G, Color.Y);
        Guess guess = new Guess();
        guess.setCode(code);
        String gameKey = "Teste-game-key";
        guess.setGameKey(gameKey);

        Assert.assertNotNull(guess.getCode());
        Assert.assertThat(guess.getCode().size(), IsEqual.equalTo(8));
        Assert.assertThat(guess.getCode(), IsEqual.equalTo(code));
        Assert.assertThat(guess.getGameKey(), IsEqual.equalTo(gameKey));

    }
}
