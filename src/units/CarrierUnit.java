package units;

public class CarrierUnit implements Unit {
    private int resourceCost = 3;
    private int combatValue = 9;
    private int movementSpeed = 1;
    private int capacity = 6;
    private String owner;

    public CarrierUnit(int resourceCost, int combatValue, int movementSpeed, int capacity, String owner) {
        this.resourceCost = resourceCost;
        this.combatValue = combatValue;
        this.movementSpeed = movementSpeed;
        this.capacity = capacity;
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
    public int getOwner(String owner) {
        return 0;
    }
}
