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
        //Referencia na metodu
        players.forEach(PlayerView::printPlayer);
    }

    public static void printPlayer(PlayerDTO playerDTO){
        System.out.println("ID: "+playerDTO.getPlayerID());
        System.out.println("Player's name: "+playerDTO.getFirstName()+" "+playerDTO.getSecondName());
        System.out.println("Date of Birth: "+playerDTO.getDateOfBirth());
        System.out.println("Positions: "+playerDTO.getBaseballPosition());
    }

    public static void printError(Exception error) {
        System.err.println(error.getMessage());
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
