package game.exceptions;

public class PlanetExistsMoreThanOnceInGalaxyException extends Exception {

    public PlanetExistsMoreThanOnceInGalaxyException() {
        super("ERROR: A planet exists more than once in the galaxy.");
    }
}
