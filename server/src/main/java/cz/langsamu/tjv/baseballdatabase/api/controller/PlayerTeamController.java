package cz.langsamu.tjv.baseballdatabase.api.controller;

import cz.langsamu.tjv.baseballdatabase.business.AwardService;
import cz.langsamu.tjv.baseballdatabase.business.PlayerService;
import cz.langsamu.tjv.baseballdatabase.business.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PlayerTeam")
public class PlayerTeamController {
    private final TeamService teamService;
    private final PlayerService playerService;

    @Autowired
    public PlayerTeamController(TeamService teamService, PlayerService playerService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @PostMapping("/addingPlayerTeam/{teamID}/{playerID}")
    public void registerNewPlayerAward(@PathVariable Long teamID, @PathVariable Long playerID ){
        playerService.findByIdPlayer(playerID);
        teamService.findByIdTeam(teamID);
        playerService.addTeam(teamID,playerID);
        teamService.addPlayer(teamID,playerID);
    }
}
