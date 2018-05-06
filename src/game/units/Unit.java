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
