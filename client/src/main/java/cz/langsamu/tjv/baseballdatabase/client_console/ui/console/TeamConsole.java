package cz.langsamu.tjv.baseballdatabase.client_console.ui.console;

import cz.langsamu.tjv.baseballdatabase.client_console.data.TeamClient;
import cz.langsamu.tjv.baseballdatabase.client_console.model.Leagues;
import cz.langsamu.tjv.baseballdatabase.client_console.model.TeamDTO;
import cz.langsamu.tjv.baseballdatabase.client_console.ui.views.PlayerView;
import cz.langsamu.tjv.baseballdatabase.client_console.ui.views.TeamView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class TeamConsole {

    private final TeamClient client;

    @Autowired
    public TeamConsole(TeamClient client) {
        this.client = client;
    }

    @ShellMethod("Register new team")
    public void registerNewTeam(String name,
                                String league,
                                int yearOfEstablishment,
                                int numberOfWorldSeriesWins) {
        try {
            var team = new TeamDTO(
                    null,
                    name,
                    Leagues.valueOf(league),
                    yearOfEstablishment,
                    numberOfWorldSeriesWins,
                    null
            );

            var newTeam = client.registerNewTeam(team);
            TeamView.printTeam(newTeam);
        } catch (RuntimeException e) {
            TeamView.printError(e);
        }
    }

    @ShellMethod("Get teams by his ID")
    public void getTeamById(Long id) {
        try {
            var team = client.readById(id);
            TeamView.printTeam(team);
        } catch (RuntimeException e) {
            TeamView.printError(e);
        }
    }

    @ShellMethod("Get all teams")
    public void getAllTeams() {
        try {
            var teams = client.readAll();
            TeamView.printAllTeams(teams);
        } catch (RuntimeException e) {
            PlayerView.printError(e);
        }
    }
}
