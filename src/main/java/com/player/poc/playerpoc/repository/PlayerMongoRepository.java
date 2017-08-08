package com.player.poc.playerpoc.repository;

import com.player.poc.playerpoc.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerMongoRepository extends MongoRepository<Player, String> {
}
