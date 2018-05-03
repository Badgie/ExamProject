import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayer() {
        Player player = new Player("PickleRick", "Pickle", "Green");
        assertEquals("PickleRick", player.getName());
        assertEquals("Pickle", player.getRace());
        assertEquals("Green", player.getColor());
    }
}