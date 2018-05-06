package game.exceptions;

public class PrintException extends Exception {
    String message = null;

    public PrintException() {
        super();
    }

    public PrintException(String message) {
        super(message);
        this.message = message;
    }
}
