package cz.langsamu.tjv.baseballdatabase.api.controller;


import cz.langsamu.tjv.baseballdatabase.api.converter.PlayerConverter;
import cz.langsamu.tjv.baseballdatabase.api.dto.PlayerDTO;
import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.business.PlayerService;
import cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("baseballDatabase/")
public class PlayerController {

    private final PlayerService playerService;

    //@Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public List<Player> getPlayers(){
        return playerService.getPlayers();
    }

    @PostMapping("/players")
    public void registerNewPlayer(@RequestBody Player player){
        playerService.addNewPlayer(player);
    }

    @DeleteMapping("/players/{playerID}")
    public void removePlayer(@RequestBody Player player, @PathVariable Long playerID){
        playerService.deletePlayer(player);
    }

    @PostMapping("/players/{playerID}")
    PlayerDTO getOne(@PathVariable Long playerID){
        return PlayerConverter.fromModel(playerService.readById(playerID).orElseThrow(NoEntityFoundException::new));
    }

    @PutMapping("/players/{playerID}")
    PlayerDTO updatePlayer(@PathVariable("playerID")long id,
                             @RequestBody PlayerDTO playerDTO) {
        playerService.updatePlayer(PlayerConverter.toModel(playerDTO));
        return getOne(playerDTO.getPlayerID());

    }

}
