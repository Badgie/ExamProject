import game.planets.Planet;
import game.player.Player;
import game.systems.HexaSystem;
import org.junit.jupiter.api.Test;
import game.units.CarrierUnit;
import game.units.CruiserUnit;
import game.units.DestroyerUnit;
import game.units.DreadnoughtUnit;

import static org.junit.jupiter.api.Assertions.*;

class HexaSystemTest {

    @Test
    void testHexaSystem() {
        HexaSystem hSystem = new HexaSystem("North");
        Player red = new Player("CronenbergRick", "Cronenberg", "Red");
        Player blue = new Player("CronenbergMorty", "Cronenberg", "Blue");

        hSystem.addShip(new DestroyerUnit(red));
        hSystem.addShip(new DreadnoughtUnit(red));
        hSystem.addShip(new CarrierUnit(red));

        hSystem.addShip(new CruiserUnit(blue));
        hSystem.addShip(new CarrierUnit(blue));

        // Test system cardinal direction
        assertEquals("North", hSystem.getCardinal());

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
    void addShip() {
        HexaSystem hSystem = new HexaSystem("North");
        Player blue = new Player("Hammerhead Morty", "Hammerhead", "Blue");
        hSystem.addShip(new DestroyerUnit(blue));

        assertTrue(hSystem.getSystemShips().get(0).getUnitType().equals("Destroyer"));
    }

    @Test
    void removeShip() {
        HexaSystem hSystem = new HexaSystem("North");
        Player blue = new Player("Mr. Meeseeks", "Meeseek", "Blue");
        CarrierUnit carrier = new CarrierUnit(blue);

        hSystem.addShip(carrier);

        assertTrue(hSystem.getSystemShips().contains(carrier));

        hSystem.removeShip(carrier);

        assertFalse(hSystem.getSystemShips().contains(carrier));
    }

    @Test
    void addPlanet() {
        HexaSystem hSystem = new HexaSystem("North");
        Planet planet = new Planet("Earth dimension C-137");

        hSystem.addPlanet(planet);

        assertTrue(hSystem.getSystemPlanets().contains(planet));
    }
}