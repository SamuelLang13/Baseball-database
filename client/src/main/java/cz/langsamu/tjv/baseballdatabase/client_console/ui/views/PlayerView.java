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

public class PlayerView extends View {

    public static void printAllPlayers(Collection<PlayerDTO> players) {
        //Referencia na metodu
        players.forEach(PlayerView::printPlayer);
    }

    public static void printPlayer(PlayerDTO playerDTO) {
        System.out.println("ID: " + playerDTO.getPlayerID());
        System.out.println("Player's name: " + playerDTO.getFirstName() + " " + playerDTO.getSecondName());
        System.out.println("Date of Birth: " + playerDTO.getDateOfBirth());
        System.out.println("Positions: " + playerDTO.getBaseballPosition());
        System.out.println("Awards: ");
        playerDTO.getAwards().forEach(System.out::println);
        System.out.println("Team");
        if (playerDTO.getTeam() != null) {
            System.out.println(playerDTO.getTeam());
        } else {
            System.out.println("No Team yet");
        }
    }
}
