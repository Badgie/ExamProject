package units;

public class CruiserUnit implements Unit {
    int resourceCost;
    int combatValue;
    int movementSpeed;
    int capacity;
    Object owner;

    public CruiserUnit(Object owner) {
        this.resourceCost = 2;
        this.combatValue = 7;
        this.movementSpeed = 2;
        this.capacity = 0;
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
    public Object getOwner() {
        return this.owner;
    }
}
