package cz.langsamu.tjv.baseballdatabase.client_console.ui.views;

import org.springframework.stereotype.Component;

@Component
public abstract class View {

    public static void printError(Exception error) {
        System.err.println(error.getMessage());
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
