package units;

public interface Unit {

    int getResourceCost(int resourceCost);

    int getCombatValue(int combatValue);

    int getMovementSpeed(int movementSpeed);

    int getCapacity(int capacity);

    String getOwner(String owner);
}
