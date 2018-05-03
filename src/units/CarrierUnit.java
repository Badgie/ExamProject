package units;

public class CarrierUnit implements Unit {
    int resourceCost;
    int combatValue;
    int movementSpeed;
    int capacity;
    Object owner;

    public CarrierUnit(Object owner) {
        this.resourceCost = 3;
        this.combatValue = 9;
        this.movementSpeed = 1;
        this.capacity = 6;
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
