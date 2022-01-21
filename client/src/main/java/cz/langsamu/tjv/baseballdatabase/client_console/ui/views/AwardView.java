package cz.langsamu.tjv.baseballdatabase.client_console.ui.views;

import cz.langsamu.tjv.baseballdatabase.client_console.model.AwardDTO;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.shell.ExitRequest;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.format.DateTimeParseException;
import java.util.Collection;

public class AwardView {
    public static void printAllPlayers(Collection<AwardDTO> awards){
        awards.forEach(awardDTO -> System.out.println("'"+awardDTO.getID()+"'"+awardDTO.getName()));
    }

    public static void printPlayer(AwardDTO awardDTO){
        System.out.println("ID: "+awardDTO.getID());
        System.out.println("Award's name: "+awardDTO.getName());
    }

    public static void printErrorGeneric(Throwable e){
        if(e instanceof WebClientResponseException){
            System.err.println("Cannot connect to API");
            throw new ExitRequest();
        }else if(e instanceof DateTimeParseException){
            System.err.println("Invalid date format - use yyy-MM-dd");
        }else {
            System.err.println("Unknown error"+e.toString());
        }
    }

    public static void printErrorCreate(WebClientException e){
        if(e instanceof WebClientResponseException.Conflict){
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Cannot create - award already exist",
                    AnsiColor.DEFAULT));
        }else{
            printErrorGeneric(e);
        }
    }

    public static void printErrorUpdate(Throwable e) {
        if (e instanceof WebClientResponseException.NotFound) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Cannot update - award does not exist",
                    AnsiColor.DEFAULT));
        } else{
            printErrorGeneric(e);
        }
    }

    public static void printErrorUser(Throwable e) {
        if (e instanceof WebClientResponseException.NotFound) {
            System.err.println(AnsiOutput.toString(
                    AnsiColor.RED
                    , "Cannot print - award does not exist",
                    AnsiColor.DEFAULT
            ));
        } else
            printErrorGeneric(e);
    }

}
