package units;

public class DestroyerUnit implements Unit {
    int resourceCost;
    int combatValue;
    int movementSpeed;
    int capacity;
    Object owner;

    public DestroyerUnit(Object owner) {
        this.resourceCost = 1;
        this.combatValue = 9;
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
