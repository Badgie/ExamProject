/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.exceptions;

public class PlanetExistsMoreThanOnceInGalaxyException extends Exception {

    public PlanetExistsMoreThanOnceInGalaxyException() {
        super("ERROR: A planet exists more than once in the galaxy.");
    }
}
