package cz.langsamu.tjv.baseballdatabase.api.converter;

import cz.langsamu.tjv.baseballdatabase.api.dto.PlayerDTO;
import cz.langsamu.tjv.baseballdatabase.api.dto.TeamDTO;
import cz.langsamu.tjv.baseballdatabase.domain.Player;
import cz.langsamu.tjv.baseballdatabase.domain.Team;

import java.util.Collection;
import java.util.stream.Collectors;

public class TeamConverter {

    public static Team toModel(TeamDTO teamDTO){
        return new Team(teamDTO.getName(), teamDTO.getLeague(),teamDTO.getYearOfEstablish(),teamDTO.getNumOfWorldSeriesWin());
    }

    public static TeamDTO fromModel(Team team){
        return new TeamDTO(team.getTeamID(), team.getName(), team.getLeague(),team.getYearOfEstablish(), team.getNumOfWorldSeriesWin(), team.getPlayersName());
    }

    public static Collection<Team> toModels(Collection<TeamDTO> teamDTO){
        return  teamDTO.stream().map(TeamConverter::toModel).collect(Collectors.toList());
    }

    public static Collection<TeamDTO> fromModels(Collection<Team> team){
        return team.stream().map(TeamConverter::fromModel).collect(Collectors.toList());
    }

}
