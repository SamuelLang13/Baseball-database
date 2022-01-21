package cz.langsamu.tjv.baseballdatabase.client_console.ui.views;

import cz.langsamu.tjv.baseballdatabase.client_console.model.PlayerDTO;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.shell.ExitRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.format.DateTimeParseException;
import java.util.Collection;

@Component
public class PlayerView {

    public static void printAllPlayers(Collection<PlayerDTO> players){
        players.forEach(playerDTO -> System.out.println("'"+playerDTO.getPlayerID()+"'"+playerDTO.getFirstName()+" "+playerDTO.getSecondName()+" "+playerDTO.getDateOfBirth()));
    }

    public static void printPlayer(PlayerDTO playerDTO){
        System.out.println("ID: "+playerDTO.getPlayerID());
        System.out.println("Player's name: "+playerDTO.getFirstName()+" "+playerDTO.getSecondName());
        System.out.println("Date of Birth: "+playerDTO.getDateOfBirth());
    }

    public static void printError(Exception error) {
        System.err.println(error.getMessage());
    }
}
