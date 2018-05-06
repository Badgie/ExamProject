package units;

public interface Unit {

    int getResourceCost();

    int getCombatValue();

    int getMovementSpeed();

    int getCapacity();

    String getUnitType();

    Object getOwner();
}
