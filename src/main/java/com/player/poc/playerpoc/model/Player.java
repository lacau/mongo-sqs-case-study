package com.player.poc.playerpoc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@NoArgsConstructor
@Getter
@Setter
public class Player {

    @Id
    public String id;

    public Integer count;
}
