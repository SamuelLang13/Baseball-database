package cz.langsamu.tjv.baseballdatabase.client_console.ui;

import cz.langsamu.tjv.baseballdatabase.client_console.data.PlayerClient;
import cz.langsamu.tjv.baseballdatabase.client_console.data.TeamClient;
import org.jline.utils.AttributedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class BaseballDatabasePrompt implements PromptProvider {

    private final PlayerClient playerClient;
    private final TeamClient teamClient;

    @Autowired
    public BaseballDatabasePrompt(PlayerClient playerClient, TeamClient teamClient) {
        this.playerClient = playerClient;
        this.teamClient = teamClient;
    }

    @Override
    public AttributedString getPrompt() {
        if(playerClient.getCurrentPlayer() != null) {
            return new AttributedString(playerClient.getCurrentPlayer().getSecondName()+":>");
        } else if(teamClient.getCurrentTeam() != null) {
            return new AttributedString(teamClient.getCurrentTeam().getName()+":>");
        }
        return new AttributedString("baseball:>");
    }
}
