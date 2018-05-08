/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.units;

import game.player.Player;

public interface Unit {

    int getResourceCost();

    int getCombatValue();

    int getMovementSpeed();

    int getCapacity();

    String getUnitType();

    Player getOwner();
}
