package com.marceldias.service;

import com.marceldias.model.Game;
import com.marceldias.model.GuessResult;
import com.marceldias.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceBean implements GameService {

    @Autowired
    GameRepository gameRepository;


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
}
