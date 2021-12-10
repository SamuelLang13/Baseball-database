package cz.langsamu.tjv.baseballdatabase.api.controller;
import cz.langsamu.tjv.baseballdatabase.api.dto.PlayerAwardDTO;
import cz.langsamu.tjv.baseballdatabase.business.AwardService;
import cz.langsamu.tjv.baseballdatabase.business.PlayerService;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/PlayerAward")
public class PlayerAwardController {

    private final AwardService awardService;
    private final PlayerService playerService;

    @Autowired
    public PlayerAwardController(AwardService awardService, PlayerService playerService) {
        this.awardService = awardService;
        this.playerService = playerService;
    }

    @PostMapping("/addingPlayerAward")
    public void registerNewPlayerAward(@RequestBody PlayerAwardDTO playerAwardDTO){
        awardService.addPlayer(playerAwardDTO.getPlayerID(),playerAwardDTO.getAwardID());
    }
    @DeleteMapping("/deletePlayersAward/{id}")
    public void removePlayersAward(@PathVariable Long id ,@RequestBody PlayerAwardDTO playerAwardDTO){
        playerService.removeAward(id,playerAwardDTO.getAwardID());
    }

    @DeleteMapping("/deleteAwardsPlayer/{id}")
    public void removeAwardsPlayer(@PathVariable Long id ,@RequestBody PlayerAwardDTO playerAwardDTO){
        awardService.removePlayer(id,playerAwardDTO.getPlayerID());
    }

}
