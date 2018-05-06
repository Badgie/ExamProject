package game.units;

import game.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestroyerUnitTest {

    @Test
    void testDestroyerUnit() {
        Player player = new Player("EyeHoleMan", "Eyehole", "Blue");
        DestroyerUnit destroyer = new DestroyerUnit(player);
        assertEquals(1, destroyer.resourceCost);
        assertEquals(9, destroyer.combatValue);
        assertEquals(2, destroyer.movementSpeed);
        assertEquals(0, destroyer.capacity);
        assertEquals(player, destroyer.owner);
    }

}