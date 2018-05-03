package units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestroyerUnitTest {

    @Test
    void testDestroyerUnit() {
        DestroyerUnit destroyer = new DestroyerUnit("EyeholeMan");
        assertEquals(1, destroyer.resourceCost);
        assertEquals(9, destroyer.combatValue);
        assertEquals(2, destroyer.movementSpeed);
        assertEquals(0, destroyer.capacity);
        assertEquals("EyeholeMan", destroyer.owner);
    }

}