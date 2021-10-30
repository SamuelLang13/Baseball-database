package cz.langsamu.tjv.baseballdatabase.api.controller;

import cz.langsamu.tjv.baseballdatabase.api.converter.TeamConverter;
import cz.langsamu.tjv.baseballdatabase.api.dto.TeamDTO;
import cz.langsamu.tjv.baseballdatabase.api.exception.NoEntityFoundException;
import cz.langsamu.tjv.baseballdatabase.business.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @GetMapping("/teams")
    public Collection<TeamDTO> getAllTeams(){
        return TeamConverter.fromModels(teamService.readAll());
    }

    @PostMapping("/teams/{teamID}")
    public TeamDTO getOneTeam(@PathVariable Long teamID){
        return TeamConverter.fromModel(teamService.readById(teamID).orElseThrow(NoEntityFoundException::new));
    }

    @PostMapping("/teams}")
    public TeamDTO registerNewTeam(@RequestBody TeamDTO teamDTO){
        teamService.create(TeamConverter.toModel(teamDTO));
        return getOneTeam(teamDTO.getTeamID());
    }

    @DeleteMapping("/teams/{teamID}")
    public void removeTeam(@RequestBody TeamDTO teamDTO,@PathVariable Long teamID){
        teamService.deleteById(teamID);
    }

    @PutMapping("/teams/{teamID}")
    public TeamDTO updateTeam(@PathVariable Long teamID,
                       @RequestBody TeamDTO teamDTO){
        teamService.update(TeamConverter.toModel(teamDTO));
        return getOneTeam(teamDTO.getTeamID());
    }

}
