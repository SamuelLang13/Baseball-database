package cz.langsamu.tjv.baseballdatabase.client_console.ui.console;

import cz.langsamu.tjv.baseballdatabase.client_console.data.PlayerAwardClient;
import cz.langsamu.tjv.baseballdatabase.client_console.ui.views.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class PlayerAwardConsole {

    private final PlayerAwardClient client;

    @Autowired
    public PlayerAwardConsole(PlayerAwardClient client) {
        this.client = client;
    }

    @ShellMethod("Register player award")
    public void registerPlayerAward(Long playerID, Long awardID) {
        try {
            client.registerPlayerAward(awardID, playerID);
            View.printMessage("Award successfully registered!");
        } catch (RuntimeException e) {
            View.printError(e);
        }
    }
}
