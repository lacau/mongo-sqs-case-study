package com.player.poc.playerpoc.config;

import com.player.poc.playerpoc.DataBaseType;
import com.player.poc.playerpoc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PlayerServiceConfig {

    @Autowired
    private PlayerService playerServiceMongoImpl;

    @Autowired
    private PlayerService playerServiceDynamoImpl;

    @Bean("dataBaseServices")
    public Map<DataBaseType, PlayerService> getDataBaseServices() {
        final Map<DataBaseType, PlayerService> map = new HashMap<>();
        map.put(DataBaseType.MONGO, playerServiceMongoImpl);
        map.put(DataBaseType.DYNAMO, playerServiceDynamoImpl);

        return map;
    }
}
