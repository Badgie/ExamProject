package units;

public class CruiserUnit implements Unit {
    private int resourceCost = 2;
    private int combatValue = 7;
    private int movementSpeed = 2;
    private int capacity = 0;
    private String owner;

    public CruiserUnit(int resourceCost, int combatValue, int movementSpeed, int capacity, String owner) {
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
