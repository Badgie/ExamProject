package units;

public class DreadnoughtUnit implements Unit {
    int resourceCost;
    int combatValue;
    int movementSpeed;
    int capacity;
    String unitType;
    Object owner;

    public DreadnoughtUnit(Object owner) {
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
    public Object getOwner() {
        return this.owner;
    }
}
