package cz.langsamu.tjv.baseballdatabase.client_console.ui.console;

import cz.langsamu.tjv.baseballdatabase.client_console.data.PlayerTeamClient;
import cz.langsamu.tjv.baseballdatabase.client_console.ui.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class PlayerTeamConsole {

    private final PlayerTeamClient client;

    @Autowired
    public PlayerTeamConsole(PlayerTeamClient client) {
        this.client = client;
    }

    @ShellMethod("Register new player team")
    public void registerNewPlayerTeam(Long playerID, Long teamID) {
        try {
            client.registerPlayerAward(playerID, teamID);
            View.printMessage("Successfully added!");
        } catch (RuntimeException e) {
            View.printError(e);
        }
    }
}
