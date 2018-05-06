package units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DreadnoughtUnitTest {

    @Test
    void testDreadnoughtUnit() {
        DreadnoughtUnit dreadnought = new DreadnoughtUnit("RealFakeDoors");
        assertEquals(5, dreadnought.resourceCost);
        assertEquals(5, dreadnought.combatValue);
        assertEquals(1, dreadnought.movementSpeed);
        assertEquals(0, dreadnought.capacity);
        assertEquals("RealFakeDoors", dreadnought.owner);
    }

}