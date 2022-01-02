package cz.langsamu.tjv.baseballdatabase.api.controller;

import cz.langsamu.tjv.baseballdatabase.api.converter.TeamConverter;
import cz.langsamu.tjv.baseballdatabase.api.dto.TeamDTO;
import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.business.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @GetMapping
    public Collection<TeamDTO> getAllTeams(){
        return TeamConverter.fromModels(teamService.readAll());
    }

    @PostMapping("/{teamID}")
    public TeamDTO getOneTeam(@PathVariable Long teamID){
        return TeamConverter.fromModel(teamService.readById(teamID).orElseThrow(NoEntityFoundException::new));
    }

    @PostMapping
    public TeamDTO registerNewTeam(@RequestBody TeamDTO teamDTO){
       return TeamConverter.fromModel(teamService.create(TeamConverter.toModel(teamDTO)));

    }

    @DeleteMapping("/{teamID}")
    public void removeAward(@PathVariable Long teamID){
        teamService.deleteById(teamID);
    }

    @PutMapping("/{teamID}")
    public TeamDTO updateTeam(@PathVariable Long teamID,
                       @RequestBody TeamDTO teamDTO){
       return TeamConverter.fromModel(teamService.update(teamID, TeamConverter.toModel(teamDTO)));
    }

}
