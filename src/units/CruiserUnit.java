package units;

public class CruiserUnit implements Unit {
    int resourceCost;
    int combatValue;
    int movementSpeed;
    int capacity;
    String owner;

    public CruiserUnit(String owner) {
        this.resourceCost = 2;
        this.combatValue = 7;
        this.movementSpeed = 2;
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
