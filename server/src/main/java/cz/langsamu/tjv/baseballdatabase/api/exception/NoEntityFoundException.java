package cz.langsamu.tjv.baseballdatabase.api.exception;

public class NoEntityFoundException extends RuntimeException {

    public NoEntityFoundException() {
        super("No entity found");
    }

    public NoEntityFoundException(String s) {
        super(s);
    }
}
