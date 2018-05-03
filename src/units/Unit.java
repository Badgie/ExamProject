package units;

public interface Unit {

    int getResourceCost();

    int getCombatValue();

    int getMovementSpeed();

    int getCapacity();

    Object getOwner();
}
