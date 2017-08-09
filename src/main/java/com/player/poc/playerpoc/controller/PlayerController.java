package com.player.poc.playerpoc.controller;

import com.player.poc.playerpoc.model.Player;
import com.player.poc.playerpoc.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayerController {

    @Autowired
    private PlayerService playerServiceMongoImpl;


    @RequestMapping(path = "/player", method = RequestMethod.POST)
    public @ResponseBody
    Player post(@RequestBody Player player) {
        final Player saved = playerServiceMongoImpl.save(player);
        //
        return saved;
    }

    @RequestMapping(path = "/player/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Player get(@PathVariable String id) {
        final Player player = playerServiceMongoImpl.findById(id);
        //
        return player;
    }

    @RequestMapping(path = "/player/{id}/increment", method = RequestMethod.POST)
    public ResponseEntity incrementOne(@PathVariable String id) {
        playerServiceMongoImpl.incrementOne(id);
        //
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/player/list", method = RequestMethod.GET)
    public @ResponseBody
    List<Player> list() {
        final List<Player> players = playerServiceMongoImpl.findAll();
        //
        return players;
    }

    @RequestMapping(path = "/player/clear", method = RequestMethod.POST)
    public ResponseEntity clear() {
        playerServiceMongoImpl.removeAll();
        //
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/player/msg", method = RequestMethod.POST)
    public @ResponseBody
    Player postMessage(@RequestBody Player player) {
        playerServiceMongoImpl.sendMessage(player);
        return player;
    }

    @RequestMapping(path = "/player/count-msg", method = RequestMethod.GET)
    public @ResponseBody
    String getMessagesCount() {
        return playerServiceMongoImpl.getMessagesCount();
    }
}
