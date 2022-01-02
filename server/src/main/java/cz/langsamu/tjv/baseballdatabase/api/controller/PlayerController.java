package cz.langsamu.tjv.baseballdatabase.api.controller;
import cz.langsamu.tjv.baseballdatabase.api.converter.PlayerConverter;
import cz.langsamu.tjv.baseballdatabase.api.dto.PlayerDTO;
import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.business.PlayerService;
import cz.langsamu.tjv.baseballdatabase.domain.Team;
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

    @GetMapping
    public Collection<PlayerDTO> getPlayers(){
        return PlayerConverter.fromModels(playerService.readAll());
    }

    @PostMapping("/{playerID}")
    public PlayerDTO getOnePlayer(@PathVariable Long playerID){
        return PlayerConverter.fromModel(playerService.readById(playerID).orElseThrow(NoEntityFoundException::new));
    }

    @PostMapping
    public PlayerDTO registerNewPlayer(@RequestBody PlayerDTO playerDTO){
        return PlayerConverter.fromModel(playerService.create(PlayerConverter.toModel(playerDTO)));
    }

    @DeleteMapping("/{playerID}")
    public void removePlayer(@PathVariable Long playerID){
        playerService.deletePlayer(playerID);
    }

    @PutMapping("/{playerID}")
    public PlayerDTO updatePlayer(@PathVariable Long playerID,
                             @RequestBody PlayerDTO playerDTO) {
       return PlayerConverter.fromModel(playerService.update(playerID,PlayerConverter.toModel(playerDTO)));
    }

}
