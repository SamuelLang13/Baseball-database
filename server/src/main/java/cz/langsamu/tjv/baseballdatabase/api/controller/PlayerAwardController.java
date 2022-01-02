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
        playerService.addAward(awardID,playerID);
    }

}
