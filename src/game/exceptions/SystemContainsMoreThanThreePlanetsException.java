/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.exceptions;

public class SystemContainsMoreThanThreePlanetsException extends Exception {

    public SystemContainsMoreThanThreePlanetsException() {
        super("ERROR: A system contains more than three planets.");
    }
}
