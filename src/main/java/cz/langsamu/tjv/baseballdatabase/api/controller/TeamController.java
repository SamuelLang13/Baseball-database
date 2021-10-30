package cz.langsamu.tjv.baseballdatabase.api.controller;

import cz.langsamu.tjv.baseballdatabase.business.TeamService;
import cz.langsamu.tjv.baseballdatabase.domain.BaseballPositions;
import cz.langsamu.tjv.baseballdatabase.domain.Leagues;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import cz.langsamu.tjv.baseballdatabase.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//@RestController
//public class TeamController {
//
//    private final TeamService teamService;
//
//    //@Autowired
//    public TeamController(TeamService teamService) {
//        this.teamService = teamService;
//    }
//
//    @GetMapping("teams")
//    public List<Team> getTeams(){
//        return teamService.getTeams();
//    }
//
//    @PostMapping
//    public void registerNewTeam(@RequestBody Team team){
//        teamService.addNewTeam(team);
//    }
//
//    @DeleteMapping(path = "{teamID}")
//    public void removeTeam(@RequestBody Team team, @PathVariable String teamID){
//        teamService.deleteTeam(team);
//    }
//
//    @PutMapping(path = "{teamID}")
//    public void updateTeam(@PathVariable("teamID")long id,
//                           @RequestParam(required=false)String name,
//                           @RequestParam(required = false)Leagues league,
//                           @RequestParam(required = false) Date yearOfEstablish,
//                           @RequestParam(required = false) int numOfWorldSeriesWin){
//
//            teamService.updateTeam(id,name,league,yearOfEstablish,numOfWorldSeriesWin);
//    }
//}
