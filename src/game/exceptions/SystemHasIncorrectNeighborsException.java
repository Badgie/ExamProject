package game.exceptions;

public class SystemHasIncorrectNeighborsException extends Exception {

    public SystemHasIncorrectNeighborsException() {
        super("ERROR: A system has incorrect neighbors.");
    }

    public SystemHasIncorrectNeighborsException(String cardinal) {
        super("ERROR: The" + cardinal + "system has incorrect neighbors.");
    }

    public SystemHasIncorrectNeighborsException(String error, String message) {
        super(error + ": " + message);
    }
}
