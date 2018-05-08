/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.exceptions;

public class GalaxyNotLegalException extends Exception {

    public GalaxyNotLegalException() {
        super("ERROR: Galaxy is not legal");
    }
}
