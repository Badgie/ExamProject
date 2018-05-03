package units;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarrierUnitTest {

    @Test
    void testCarrierUnit() {
        CarrierUnit carrier = new CarrierUnit("Gazorpazorpfield");
        assertEquals(3, carrier.resourceCost);
        assertEquals(9, carrier.combatValue);
        assertEquals(1, carrier.movementSpeed);
        assertEquals(6, carrier.capacity);
        assertEquals("Gazorpazorpfield", carrier.owner);
    }
}