package cz.langsamu.tjv.baseballdatabase.api.controller;


import cz.langsamu.tjv.baseballdatabase.api.converter.PlayerConverter;
import cz.langsamu.tjv.baseballdatabase.api.dto.PlayerDTO;
import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.business.PlayerService;
import cz.langsamu.tjv.baseballdatabase.domain.Award;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import cz.langsamu.tjv.baseballdatabase.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

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
        Team team = playerService.findTeam(playerDTO.getTeamID());
        Set<Award> awards = playerService.findAward(playerDTO.getAwardIDs());
        playerDTO.setTeam(team);
        playerDTO.setAwards(awards);
        return PlayerConverter.fromModel(playerService.create(PlayerConverter.toModel(playerDTO)));
    }

    @DeleteMapping("/{playerID}")
    public void removePlayer(@PathVariable Long playerID){
        playerService.deleteById(playerID);
    }

    @PutMapping("/{playerID}")
    public PlayerDTO updatePlayer(@PathVariable("playerID")long playerID,
                             @RequestBody PlayerDTO playerDTO) {
       return PlayerConverter.fromModel(playerService.update(playerID,PlayerConverter.toModel(playerDTO)));
    }

}
