package cz.langsamu.tjv.baseballdatabase.client_console.ui;

import cz.langsamu.tjv.baseballdatabase.client_console.data.PlayerClient;
import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class BaseballDatabasePrompt implements PromptProvider {

    private PlayerClient playerClient;

    public BaseballDatabasePrompt(PlayerClient playerClient) {
        this.playerClient = playerClient;
    }


    @Override
    public AttributedString getPrompt() {
        return null;
    }
}
