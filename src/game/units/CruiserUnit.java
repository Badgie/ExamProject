/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.units;

import game.player.Player;

public class CruiserUnit implements Unit {
    int resourceCost;
    int combatValue;
    int movementSpeed;
    int capacity;
    String unitType;
    Player owner;

    public CruiserUnit(Player owner) {
        this.resourceCost = 2;
        this.combatValue = 7;
        this.movementSpeed = 2;
        this.capacity = 0;
        this.unitType = "Cruiser";
        this.owner = owner;
    }

    @Override
    public int getResourceCost() {
        return this.resourceCost;
    }

    @Override
    public int getCombatValue() {
        return this.combatValue;
    }

    @Override
    public int getMovementSpeed() {
        return this.movementSpeed;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public String getUnitType() {
        return this.unitType;
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }

    @Override
    public String toString() {
        return "CruiserUnit{" + "resourceCost=" + resourceCost + ", combatValue=" + combatValue + ", movementSpeed=" + movementSpeed + ", capacity=" + capacity + ", unitType='" + unitType + '\'' + ", owner=" + owner + '}';
    }
}
