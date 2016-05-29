package com.marceldias.model;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class GameTest {

    User user;
    String username;

    @Before
    public void setUp() {
        username = "Test-"+System.currentTimeMillis();
        user = new User(username);
    }

    @Test
    public void testCreateNewGame() {
        Game game = new Game(user);
        Assert.assertNotNull(game.getCreatedAt());
        Assert.assertNotNull(game.getCode());
        Assert.assertNotNull(game.getUser());
        Assert.assertThat(game.getUser().getName(), IsEqual.equalTo(username));
    }

    @Test
    public void testCreateNewGameWithoutUser() {
        Game game = new Game();
        Assert.assertNotNull(game.getCreatedAt());
        Assert.assertNotNull(game.getCode());
        Assert.assertNull(game.getUser());
    }

    @Test
    public void testExpiredGame() {
        Game game = new Game(user);
        game.setCreatedAt(LocalDateTime.now().minusMinutes(15l));
        Assert.assertThat(game.isExpired(), Is.is(Boolean.TRUE));
    }

    @Test
    public void testNonExpiredGame() {
        Game game = new Game(user);
        Assert.assertThat(game.isExpired(), Is.is(Boolean.FALSE));
    }

    @Test
    public void testGuessCount() {
        Game game = new Game(user);
        Assert.assertThat(game.getGuessesCount(), Is.is(0));
    }

    @Test
    public void testGuesses() {
        Game game = new Game(user);
        Assert.assertThat(game.getGuesses().size(), Is.is(0));
        Assert.assertThat(game.getResult(), IsNull.nullValue());
    }

    @Test
    public void testGuessResult() {
        GuessResult result = new GuessResult();
        result.setExact(8);
        Game game = new Game(user);
        game.setResult(result);

        Assert.assertThat(game.getGuesses().size(), Is.is(1));
        Assert.assertThat(game.getResult(), Is.is(result));
    }
}
