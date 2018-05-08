package game.player;

import game.galaxy.Galaxy;
import game.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayer() {
        Galaxy galaxy = new Galaxy();
        Player player = new Player("PickleRick", "Pickle", galaxy);
        assertEquals("PickleRick", player.getName());
        assertEquals("Pickle", player.getRace());
        assertEquals("Green", player.getColor());
    }
}