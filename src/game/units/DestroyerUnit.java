/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.units;

import game.player.Player;

public class DestroyerUnit implements Unit {
    int resourceCost;
    int combatValue;
    int movementSpeed;
    int capacity;
    String unitType;
    Player owner;

    public DestroyerUnit(Player owner) {
        this.resourceCost = 1;
        this.combatValue = 9;
        this.movementSpeed = 2;
        this.capacity = 0;
        this.unitType = "Destroyer";
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
        return "DestroyerUnit{" + "resourceCost=" + resourceCost + ", combatValue=" + combatValue + ", movementSpeed=" + movementSpeed + ", capacity=" + capacity + ", unitType='" + unitType + '\'' + ", owner=" + owner + '}';
    }
}
