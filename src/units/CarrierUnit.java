package units;

public class CarrierUnit implements Unit {
    private int resourceCost = 3;
    private int combatValue = 9;
    private int movementSpeed = 1;
    private int capacity = 6;

    public CarrierUnit(int resourceCost, int combatValue, int movementSpeed, int capacity) {
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
