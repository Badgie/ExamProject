package units;

public class DreadnoughtUnit implements Unit {
    int resourceCost;
    int combatValue;
    int movementSpeed;
    int capacity;
    String owner;

    public DreadnoughtUnit(String owner) {
        this.resourceCost = 5;
        this.combatValue = 5;
        this.movementSpeed = 1;
        this.capacity = 0;
        this.owner = owner;
    }

    @Override
    public int getResourceCost(int resourceCost) {
        return 0;
    }

    @Override
    public int getCombatValue(int combatValue) {
        return 0;
    }

    @Override
    public int getMovementSpeed(int movementSpeed) {
        return 0;
    }

    @Override
    public int getCapacity(int capacity) {
        return 0;
    }

    @Override
    public String getOwner(String owner) {
        return owner;
    }
}
