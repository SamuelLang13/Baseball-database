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

    public void printAllPlayers(Collection<PlayerDTO> players){
        players.forEach(playerDTO -> System.out.println("'"+playerDTO.getID()+"'"+playerDTO.getFirstName()+" "+playerDTO.getSecondName()+" "+playerDTO.getDateOfBirth()));
    }

    public void printPlayer(PlayerDTO playerDTO){
        System.out.println("ID: "+playerDTO.getID());
        System.out.println("Player's name: "+playerDTO.getFirstName()+" "+playerDTO.getSecondName());
        System.out.println("Date of Birth: "+playerDTO.getDateOfBirth());
    }

    public void printErrorGeneric(Throwable e){
        if(e instanceof WebClientResponseException){
            System.err.println("Cannot connect to API");
            throw new ExitRequest();
        }else if(e instanceof WebClientResponseException.InternalServerError){
            System.err.println("Unknown technical server error");
        }else if(e instanceof DateTimeParseException){
            System.err.println("Invalid date format - use yyy-MM-dd");
        }else {
            System.err.println("Unknown error"+e.toString());
        }

    }

    public void printErrorCreate(WebClientException e){
        if(e instanceof WebClientResponseException.Conflict){
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Cannot create - player already exist",
                    AnsiColor.DEFAULT));
        }else{
            printErrorGeneric(e);
        }
    }

    public void printErrorUpdate(Throwable e) {
        if (e instanceof WebClientResponseException.NotFound) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Cannot update - player does not exist",
                    AnsiColor.DEFAULT));
        } else{
            printErrorGeneric(e);
        }
    }

    public void printErrorPlayer(Throwable e) {
        if (e instanceof WebClientResponseException.NotFound) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Cannot print - player does not exist",
                    AnsiColor.DEFAULT
            ));
        } else
            printErrorGeneric(e);
    }
}
