package game.units;

import game.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DreadnoughtUnitTest {

    @Test
    void testDreadnoughtUnit() {
        Player player = new Player("RealFakeDoors", "Door", "Brown");
        DreadnoughtUnit dreadnought = new DreadnoughtUnit(player);
        assertEquals(5, dreadnought.resourceCost);
        assertEquals(5, dreadnought.combatValue);
        assertEquals(1, dreadnought.movementSpeed);
        assertEquals(0, dreadnought.capacity);
        assertEquals(player, dreadnought.owner);
    }

}