package exceptions;

public class PrintException extends Exception {
    String message = null;

    public printMessage() {
        super();
    }

    public PrintException(String message) {
        super(message);
        this.message = message;
    }

}
