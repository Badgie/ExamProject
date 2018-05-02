package units;


public class DreadnoughtUnit implements Unit {
    private int resourceCost = 5;
    private int combatValue = 5;
    private int movementSpeed = 1;
    private int capacity = 0;

    public DreadnoughtUnit(int resourceCost, int combatValue, int movementSpeed, int capacity) {
        this.resourceCost = resourceCost;
        this.combatValue = combatValue;
        this.movementSpeed = movementSpeed;
        this.capacity = capacity;
    }

    public int getResourceCost() {
        return resourceCost;
    }

    public int getCombatValue() {
        return combatValue;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public int getCapacity() {
        return capacity;
    }
}
