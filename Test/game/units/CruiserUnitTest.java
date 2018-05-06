package units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CruiserUnitTest {

    @Test
    void testCruiserUnit() {
        CruiserUnit cruiser = new CruiserUnit("RegularOldPlumbus");
        assertEquals(2, cruiser.resourceCost);
        assertEquals(7, cruiser.combatValue);
        assertEquals(2, cruiser.movementSpeed);
        assertEquals(0, cruiser.capacity);
        assertEquals("RegularOldPlumbus", cruiser.owner);
    }
}