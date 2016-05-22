package com.marceldias.model;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class GameTest {

    User user;

    @Before
    public void setUp() {
        user = new User("Test"+System.currentTimeMillis());
    }

    @Test
    public void testCreateNewGame() {
        Game game = new Game(user);
        Assert.assertNotNull(game.getCreatedAt());
        Assert.assertNotNull(game.getCode());
        Assert.assertNotNull(game.getUser());
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
}
