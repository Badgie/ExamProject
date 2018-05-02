package units;

public class CruiserUnit implements Unit {
    private int resourceCost = 2;
    private int combatValue = 7;
    private int movementSpeed = 2;
    private int capacity = 0;

    public CruiserUnit(int resourceCost, int combatValue, int movementSpeed, int capacity) {
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
