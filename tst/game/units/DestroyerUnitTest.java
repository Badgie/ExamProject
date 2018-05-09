/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.units;

import game.galaxy.Galaxy;
import game.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestroyerUnitTest {

    @Test
    void testDestroyerUnit() {
        Galaxy galaxy = new Galaxy();
        Player player = new Player("EyeHoleMan", "Eyehole", galaxy);
        DestroyerUnit destroyer = new DestroyerUnit(player);
        assertEquals(1, destroyer.resourceCost);
        assertEquals(9, destroyer.combatValue);
        assertEquals(2, destroyer.movementSpeed);
        assertEquals(0, destroyer.capacity);
        assertEquals(player, destroyer.owner);
        assertEquals("Destroyer", destroyer.unitType);
    }

}