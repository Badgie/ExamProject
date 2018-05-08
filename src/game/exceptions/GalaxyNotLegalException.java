package game.exceptions;

public class GalaxyNotLegalException extends Exception {

    public GalaxyNotLegalException() {
        super("ERROR: Galaxy is not legal");
    }
}
