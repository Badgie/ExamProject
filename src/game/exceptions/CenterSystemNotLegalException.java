/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.exceptions;

public class CenterSystemNotLegalException extends Exception {

    public CenterSystemNotLegalException() {
        super("ERROR: Either the center system contains planets other than " +
                "Mecatol Rex, or it does not contain Mecatol Rex.");
    }
}
