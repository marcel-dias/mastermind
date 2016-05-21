package com.marceldias.repository;

import com.marceldias.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {

    Game findByKey(String key);
}
