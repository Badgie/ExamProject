/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.systems;

import game.galaxy.Galaxy;
import game.planets.Planet;
import game.player.Player;
import game.systems.HexaSystem;
import game.units.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HexaSystemTest {

    @Test
    void testHexaSystem() {
        Galaxy galaxy = new Galaxy();
        HexaSystem hSystem = new HexaSystem(galaxy);
        Player red = new Player("CronenbergRick", "Cronenberg", galaxy);
        Player blue = new Player("CronenbergMorty", "Cronenberg", galaxy);

        hSystem.addShip(new DestroyerUnit(red));
        hSystem.addShip(new DreadnoughtUnit(red));
        hSystem.addShip(new CarrierUnit(red));

        hSystem.addShip(new CruiserUnit(blue));
        hSystem.addShip(new CarrierUnit(blue));

        // Test system cardinal direction
        assertEquals("Center", hSystem.getCardinal());

        // Test ship owners
        assertTrue(hSystem.getSystemShips().get(0).getOwner().equals(red));
        assertTrue(hSystem.getSystemShips().get(1).getOwner().equals(red));
        assertTrue(hSystem.getSystemShips().get(2).getOwner().equals(red));
        assertTrue(hSystem.getSystemShips().get(3).getOwner().equals(blue));
        assertTrue(hSystem.getSystemShips().get(4).getOwner().equals(blue));

        // Test ship types
        assertTrue(hSystem.getSystemShips().get(0).getUnitType().equals("Destroyer"));
        assertTrue(hSystem.getSystemShips().get(1).getUnitType().equals("Dreadnought"));
        assertTrue(hSystem.getSystemShips().get(2).getUnitType().equals("Carrier"));
        assertTrue(hSystem.getSystemShips().get(3).getUnitType().equals("Cruiser"));
        assertTrue(hSystem.getSystemShips().get(4).getUnitType().equals("Carrier"));
    }

    @Test
    void testSetNewCardinal() {
        Galaxy galaxy = new Galaxy();

        galaxy.getSystems().add(new HexaSystem(galaxy));
        galaxy.getSystems().add(new HexaSystem(galaxy));
        galaxy.getSystems().add(new HexaSystem(galaxy));
        galaxy.getSystems().add(new HexaSystem(galaxy));
        galaxy.getSystems().add(new HexaSystem(galaxy));
        galaxy.getSystems().add(new HexaSystem(galaxy));
        galaxy.getSystems().add(new HexaSystem(galaxy));

        List<HexaSystem> systems = galaxy.getSystems();

        // systems are always generated in the same order
        assertEquals("Center", systems.get(0).getCardinal());
        assertEquals("North", systems.get(1).getCardinal());
        assertEquals("NorthEast", systems.get(2).getCardinal());
        assertEquals("NorthWest", systems.get(3).getCardinal());
        assertEquals("South", systems.get(4).getCardinal());
        assertEquals("SouthEast", systems.get(5).getCardinal());
        assertEquals("SouthWest", systems.get(6).getCardinal());
    }

    @Test
    void testAddShip() {
        Galaxy galaxy = new Galaxy();
        HexaSystem hSystem = new HexaSystem(galaxy);
        Player blue = new Player("Hammerhead Morty", "Hammerhead", galaxy);
        hSystem.addShip(new DestroyerUnit(blue));

        assertTrue(hSystem.getSystemShips().get(0).getUnitType().equals("Destroyer"));
    }

    @Test
    void testRemoveShip() {
        Galaxy galaxy = new Galaxy();
        HexaSystem hSystem = new HexaSystem(galaxy);
        Player blue = new Player("Mr. Meeseeks", "Meeseek", galaxy);
        CarrierUnit carrier = new CarrierUnit(blue);

        hSystem.addShip(carrier);

        assertTrue(hSystem.getSystemShips().contains(carrier));

        hSystem.removeShip(carrier);

        assertFalse(hSystem.getSystemShips().contains(carrier));
    }

    @Test
    void testAddPlanet() {
        Galaxy galaxy = new Galaxy();
        HexaSystem hSystem = new HexaSystem(galaxy);
        Planet planet = new Planet("Earth dimension C-137");

        hSystem.addPlanet(planet);

        assertTrue(hSystem.getSystemPlanets().contains(planet));
    }

    @Test
    void testConcludeCombat() {
        Galaxy galaxy = new Galaxy();
        galaxy.getSystems().add(new HexaSystem(galaxy));
        Player playerOne = new Player("Name", "Race", galaxy);
        Player playerTwo = new Player("NameTwo", "RaceTwo", galaxy);

        playerOne.getShips().add(new DestroyerUnit(playerOne));

        Player winner = galaxy.getSystems().get(0).concludeCombat(playerOne, playerTwo);

        assertEquals(playerOne, winner);
    }

    @Test
    void testCalculateHitsDoneByPlayerOne() {

    }

    @Test
    void testCalculateHitsDoneByPlayerTwo() {

    }

    @Test
    void testGetPlayerWorstShipInSystem() {
        Galaxy galaxy = new Galaxy().sampleGalaxy();
        HexaSystem system = galaxy.getSystems().get(0);
        Player player = system.getPlayersInSystem().get(0);

        system.setPlayerShipsInSystem(player);

        Unit worstShip = system.getPlayerWorstShipInSystem(player);

        // in sample galaxy, player has two dreadnoughts and one destroyer
        assertEquals(worstShip, player.getShips().get(0));
    }

    @Test
    void testSetPlayerShipsInSystem() {
        Galaxy galaxy = new Galaxy().sampleGalaxy();

        // in sample galaxy, player at index 0 has three ships in system at index 0
        HexaSystem system = galaxy.getSystems().get(0);
        Player player = galaxy.getPlayers().get(0);

        system.setPlayerShipsInSystem(player);

        assertEquals(3, player.getShipsInCombatSorted().size());
    }

    @Test
    void testGetPlayersInSystem() {
        Galaxy galaxy = new Galaxy().sampleGalaxy();

        // system index 0 contains 1 player in sample galaxy
        List<Player> playersInSystem = new ArrayList<>(galaxy.getSystems().get(0).getPlayersInSystem());

        assertEquals(1, playersInSystem.size());
    }
}