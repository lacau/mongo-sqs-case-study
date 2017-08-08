package com.player.poc.playerpoc.factory;

import com.player.poc.playerpoc.DataBaseType;
import com.player.poc.playerpoc.service.PlayerService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PlayerServiceFactory {

    private Map<DataBaseType, PlayerService> dataBaseServices;

    public PlayerService getPlayerService(DataBaseType dataBaseType) {
        return dataBaseServices.get(dataBaseType);
    }
}
