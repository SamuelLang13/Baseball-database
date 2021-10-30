package cz.langsamu.tjv.baseballdatabase.api.controller;


import cz.langsamu.tjv.baseballdatabase.api.converter.PlayerConverter;
import cz.langsamu.tjv.baseballdatabase.api.dto.PlayerDTO;
import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.business.PlayerService;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public Collection<PlayerDTO> getPlayers(){
        return PlayerConverter.fromModels(playerService.readAll());
    }

    @PostMapping("/players")
    public PlayerDTO registerNewPlayer(@RequestBody PlayerDTO playerDTO){
        playerService.create(PlayerConverter.toModel(playerDTO));
        return getOnePlayer(playerDTO.getPlayerID());
    }

    @DeleteMapping("/players/{playerID}")
    public void removePlayer(@RequestBody Player player, @PathVariable Long playerID){
        playerService.deleteById(playerID);
    }

    @PostMapping("/players/{playerID}")
    public PlayerDTO getOnePlayer(@PathVariable Long playerID){
        return PlayerConverter.fromModel(playerService.readById(playerID).orElseThrow(NoEntityFoundException::new));
    }

    @PutMapping("/players/{playerID}")
    public PlayerDTO updatePlayer(@PathVariable("playerID")long id,
                             @RequestBody PlayerDTO playerDTO) {
        playerService.update(PlayerConverter.toModel(playerDTO));
        return getOnePlayer(playerDTO.getPlayerID());

    }

}
