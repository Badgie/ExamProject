package units;

public class DestroyerUnit implements Unit{
    private int resourceCost = 1;
    private int combatValue = 9;
    private int movementSpeed = 2;
    private int capacity = 0;

    public DestroyerUnit(int resourceCost, int combatValue, int movementSpeed, int capacity) {
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
