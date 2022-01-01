package cz.langsamu.tjv.baseballdatabase.api.controller;
import cz.langsamu.tjv.baseballdatabase.api.dto.PlayerAwardDTO;
import cz.langsamu.tjv.baseballdatabase.business.AwardService;
import cz.langsamu.tjv.baseballdatabase.business.PlayerService;
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

    @PostMapping("/addingPlayerAward/{awardID}/{playerID}")
    public void registerNewPlayerAward(@PathVariable Long awardID, @PathVariable Long playerID ){
        playerService.findByIdPlayer(playerID);
        awardService.findByIdAward(awardID);
    }

//    @DeleteMapping("/deletePlayer/{awardID}")
//    public void deletePlayer(@PathVariable Long awardID, @RequestBody PlayerAwardDTO playerAwardDTO ){
//        awardService.deletePlayerId(awardID,playerAwardDTO.getPlayerID());
//    }
//
//    @DeleteMapping("/deleteAward/{playerID}")
//    public void deleteAward(@PathVariable Long playerID, @RequestBody PlayerAwardDTO playerAwardDTO ){
//        playerService.deleteAwardId(playerID,playerAwardDTO.getAwardID());
//    }
}
