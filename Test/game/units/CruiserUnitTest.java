package game.units;

import game.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CruiserUnitTest {

    @Test
    void testCruiserUnit() {
        Player player = new Player("RegularOldPlumbus", "Plumbus", "Pink");
        CruiserUnit cruiser = new CruiserUnit(player);
        assertEquals(2, cruiser.resourceCost);
        assertEquals(7, cruiser.combatValue);
        assertEquals(2, cruiser.movementSpeed);
        assertEquals(0, cruiser.capacity);
        assertEquals(player, cruiser.owner);
    }
}