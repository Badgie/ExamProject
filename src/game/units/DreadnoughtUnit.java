package game.units;

import game.player.Player;

public class DreadnoughtUnit implements Unit {
    int resourceCost;
    int combatValue;
    int movementSpeed;
    int capacity;
    private String unitType;
    Player owner;

    public DreadnoughtUnit(Player owner) {
        this.resourceCost = 5;
        this.combatValue = 5;
        this.movementSpeed = 1;
        this.capacity = 0;
        this.unitType = "Dreadnought";
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
        return "DreadnoughtUnit{" + "resourceCost=" + resourceCost + ", combatValue=" + combatValue + ", movementSpeed=" + movementSpeed + ", capacity=" + capacity + ", unitType='" + unitType + '\'' + ", owner=" + owner + '}';
    }
}
