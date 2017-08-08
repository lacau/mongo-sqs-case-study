package com.player.poc.playerpoc.service;

import com.player.poc.playerpoc.model.Player;
import com.player.poc.playerpoc.repository.PlayerMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class PlayerServiceMongoImpl implements PlayerService {

    @Autowired
    private PlayerMongoRepository playerMongoRepository;

    @Autowired
    private MongoOperations mongoOperations;

    private Update update;

    @PostConstruct
    private void init() {
        update = new Update().inc("count", 1);
    }

    @Override
    public Player save(Player player) {
        return playerMongoRepository.save(player);
    }

    @Override
    public void incrementOne(String id) {
        final Query query = new Query(Criteria.where("id").is(id));
        mongoOperations.updateFirst(query, update, Player.class);
    }

    @Override
    public Player findById(String id) {
        return playerMongoRepository.findOne(id);
    }

    @Override
    public List<Player> findAll() {
        return playerMongoRepository.findAll();
    }

    @Override
    public void removeAll() {
        playerMongoRepository.deleteAll();
    }
}
