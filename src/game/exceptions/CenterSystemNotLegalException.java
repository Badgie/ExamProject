package game.exceptions;

public class CenterSystemNotLegalException extends Exception {

    public CenterSystemNotLegalException() {
        super("ERROR: Either the center system contains planets other than " +
                "Mecatol Rex, or it does not contain Mecatol Rex.");
    }
}
