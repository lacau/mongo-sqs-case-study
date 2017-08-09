package com.player.poc.playerpoc.service;

import com.player.poc.playerpoc.model.Player;

import java.util.List;

public interface PlayerService {

    Player save(Player player);

    void incrementOne(String id);

    Player findById(String id);

    List<Player> findAll();

    void removeAll();

    void sendMessage(Player player);

    String getMessagesCount();
}
