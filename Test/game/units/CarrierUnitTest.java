package game.units;

import game.galaxy.Galaxy;
import game.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarrierUnitTest {

    @Test
    void testCarrierUnit() {
        Galaxy galaxy = new Galaxy();
        Player player = new Player("Gazorpazorpfield", "Cat-thing", galaxy);
        CarrierUnit carrier = new CarrierUnit(player);
        assertEquals(3, carrier.resourceCost);
        assertEquals(9, carrier.combatValue);
        assertEquals(1, carrier.movementSpeed);
        assertEquals(6, carrier.capacity);
        assertEquals(player, carrier.owner);
    }
}