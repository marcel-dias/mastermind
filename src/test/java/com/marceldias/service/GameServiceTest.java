package com.marceldias.service;

import com.marceldias.model.*;
import com.marceldias.repository.GameRepository;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;
    private Game game;
    private User user;
    private List<Color> code;
    private Guess guess;
    private String key = "Key-"+System.currentTimeMillis();

    @InjectMocks
    private GameService gameService = new GameServiceBean();

    @Before
    public void setUp() {
        user = new User("Test "+System.currentTimeMillis());
        code = Color.generateColorCode(8);
        game = new Game(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProcessWithoutGameKey() {
        guess = new Guess();
        gameService.processGuess(guess);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProcessWithExpiredGameKey() {
        guess = new Guess();
        game = Mockito.spy(game);
        Mockito.when(gameService.findByKey(Mockito.anyString())).thenReturn(game);
        Mockito.when(game.isExpired()).thenReturn(Boolean.TRUE);

        guess.setGameKey(key);
        gameService.processGuess(guess);
    }

    @Test
    public void testUpdateWinnerGuess() {
        Mockito.when(gameRepository.save(Mockito.any(Game.class))).thenReturn(game);

        GuessResult result = new GuessResult();
        result.setExact(8);
        game = gameService.update(game, result);

        Assert.assertThat(game.isSolved(), IsEqual.equalTo(Boolean.TRUE));
    }

    @Test
    public void testGuessToSolvedGame() {
        game = Mockito.spy(game);
        Mockito.when(game.isSolved()).thenReturn(Boolean.TRUE);
        Mockito.when(gameService.findByKey(Mockito.anyString())).thenReturn(game);

        guess = new Guess();
        guess.setGameKey(key);
        guess.setCode(Color.generateColorCode(8));

        Assert.assertThat(game.getGuessesCount(), IsEqual.equalTo(0));

        game = gameService.processGuess(guess);

        Assert.assertThat(game.isSolved(), IsEqual.equalTo(Boolean.TRUE));
        Assert.assertThat(game.getGuessesCount(), IsEqual.equalTo(0));
        Assert.assertThat(game.getResult(), IsNull.nullValue());
    }
}
