package cz.langsamu.tjv.baseballdatabase.client_console.ui.views;

import cz.langsamu.tjv.baseballdatabase.client_console.model.TeamDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class TeamView extends View {
    public static void printTeam(TeamDTO teamDTO) {
        System.out.println("ID: " + teamDTO.getTeamID());
        System.out.println("Name: " + teamDTO.getName());
        System.out.println("League: " + teamDTO.getLeague().name());
        System.out.println("Year of establishment: " + teamDTO.getYearOfEstablish());
        System.out.println("Number of world series wins: " + teamDTO.getNumOfWorldSeriesWin());
    }

    public static void printAllTeams(Collection<TeamDTO> teams) {
        teams.forEach(TeamView::printTeam);
    }
}
