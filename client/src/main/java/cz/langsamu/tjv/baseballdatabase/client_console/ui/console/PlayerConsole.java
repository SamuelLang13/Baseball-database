package cz.langsamu.tjv.baseballdatabase.client_console.ui.console;

import cz.langsamu.tjv.baseballdatabase.client_console.data.PlayerClient;
import cz.langsamu.tjv.baseballdatabase.client_console.model.BaseballPositions;
import cz.langsamu.tjv.baseballdatabase.client_console.model.PlayerDTO;
import cz.langsamu.tjv.baseballdatabase.client_console.ui.views.PlayerView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@ShellComponent
public class PlayerConsole {

    private final PlayerClient client;

    @Autowired
    public PlayerConsole(PlayerClient client) {
        this.client = client;
    }

    @ShellMethod("Register new player")
    public void registerPlayer(String firstName,
                               String secondName,
                               String baseballPosition,
                               String dateOfBirth) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy").withLocale(Locale.ENGLISH);
            var player = new PlayerDTO(
                    null,
                    firstName,
                    secondName,
                    BaseballPositions.valueOf(baseballPosition),
                    LocalDate.parse(dateOfBirth, formatter),
                    null
            );
            var newPlayer = client.create(player);
            PlayerView.printPlayer(newPlayer);
        } catch (RuntimeException e) {
            PlayerView.printError(e);
        }
    }

    @ShellMethod("Set current player")
    public void setCurrentPlayer(Long id) {
        try {
            var player = client.readById(id);
            client.setCurrentPlayer(player);
        } catch (RuntimeException e) {
            PlayerView.printError(e);
        }
    }

    @ShellMethod("Get player by his ID")
    public void getPlayerById(Long id) {
        try {
            var player = client.readById(id);
            PlayerView.printPlayer(player);
        } catch (RuntimeException e) {
            PlayerView.printError(e);
        }
    }

    @ShellMethod("Get all players")
    public void getAllPlayers() {
        try {
            var players = client.readAll();
            PlayerView.printAllPlayers(players);
        } catch (RuntimeException e) {
            PlayerView.printError(e);
        }
    }

    @ShellMethod("Remove player")
    @ShellMethodAvailability("playerSelected")
    public void removePlayer() {
        client.delete();
    }

    @ShellMethod("Update player")
    @ShellMethodAvailability("playerSelected")
    public void updatePlayer(String firstName,
                             String secondName,
                             String baseballPosition,
                             String dateOfBirth) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy").withLocale(Locale.ENGLISH);
            var player = new PlayerDTO(
                    null,
                    firstName,
                    secondName,
                    BaseballPositions.valueOf(baseballPosition),
                    LocalDate.parse(dateOfBirth, formatter),
                    null
            );
            var updatePlayer = client.update(player);
            PlayerView.printPlayer(updatePlayer);
        } catch (RuntimeException e) {
            PlayerView.printError(e);
        }
    }

    private Availability playerSelected() {
        return client.getCurrentPlayer() == null ? Availability.unavailable("No user selected")
                                                   : Availability.available();
    }
}