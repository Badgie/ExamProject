package game.player;

import game.galaxy.Galaxy;
import game.systems.HexaSystem;
import game.units.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayer() {
        Galaxy galaxy = new Galaxy();
        galaxy.getPlayers().add(new Player("PickleRick", "Pickle", galaxy));
        galaxy.getPlayers().add(new Player("Test1", "Test1", galaxy));
        galaxy.getPlayers().add(new Player("Test2", "Test2", galaxy));
        galaxy.getPlayers().add(new Player("Test3", "Test3", galaxy));
        galaxy.getPlayers().add(new Player("Test4", "Test4", galaxy));
        galaxy.getPlayers().add(new Player("Test5", "Test5", galaxy));

        List<Player> players = galaxy.getPlayers();

        assertEquals("PickleRick", players.get(0).getName());
        assertEquals("Pickle", players.get(0).getRace());
        assertEquals("Green", players.get(0).getColor());

        assertEquals("Red", players.get(1).getColor());
        assertEquals("Yellow", players.get(2).getColor());
        assertEquals("Purple", players.get(3).getColor());
        assertEquals("Black", players.get(4).getColor());
        assertEquals("Blue", players.get(5).getColor());
    }

    @Test
    void testGetPlayerShipsSortedHighToLow() {
        Galaxy galaxy = new Galaxy();
        galaxy.getPlayers().add(new Player("Test", "Test", galaxy));
        Player testPlayer = galaxy.getPlayers().get(0);
        testPlayer.getShips().add(new CarrierUnit(testPlayer));
        testPlayer.getShips().add(new CruiserUnit(testPlayer));
        testPlayer.getShips().add(new DestroyerUnit(testPlayer));

        List<Unit> testList = new ArrayList<>(testPlayer.getShips());

        assertEquals(3, testList.size());

        List<Unit> testListSorted = testPlayer.getPlayerShipsSortedHighToLow();

        assertNotEquals(testList, testListSorted);
    }
}