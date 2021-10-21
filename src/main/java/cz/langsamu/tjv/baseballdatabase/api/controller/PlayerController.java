package cz.langsamu.tjv.baseballdatabase.api.controller;


import cz.langsamu.tjv.baseballdatabase.dao.PlayersRepository;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("baseballDatabase/")
public class PlayerController {
    @Autowired
    private PlayersRepository playersRepository;
    @GetMapping("players")
    public List<Player> getPlayers(){
        return this.playersRepository.findAll();
    }
}
