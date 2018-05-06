import game.galaxy.Galaxy;
import game.planets.Planet;
import game.player.Player;
import game.systems.HexaSystem;
import org.junit.jupiter.api.Test;
import game.units.CarrierUnit;
import game.units.CruiserUnit;
import game.units.DestroyerUnit;
import game.units.DreadnoughtUnit;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GalaxyTest {

    @Test
    void testSampleGalaxy() {
        Galaxy galaxy = new Galaxy();

        // Players
        Player blue = new Player("Crassus", "The Emirates of Hacan", "Blue");
        Player red = new Player("Pompey", "The Federation of Sol", "Red");

        // Systems
        List<HexaSystem> sampleSystems = new ArrayList<>();
        sampleSystems.add(new HexaSystem("Center"));
        sampleSystems.add(new HexaSystem("North"));
        sampleSystems.add(new HexaSystem("North East"));
        sampleSystems.add(new HexaSystem("South East"));
        sampleSystems.add(new HexaSystem("South"));
        sampleSystems.add(new HexaSystem("South West"));
        sampleSystems.add(new HexaSystem("North West"));
        galaxy.systems.addAll(sampleSystems);

        // Add planets
        galaxy.getSystems().get(0).addPlanet(new Planet("Mecatol Rex"));
        galaxy.getSystems().get(1).addPlanet(new Planet("Vega Minor"));
        galaxy.getSystems().get(1).addPlanet(new Planet("Vega Major"));
        galaxy.getSystems().get(3).addPlanet(new Planet("Industrex"));
        galaxy.getSystems().get(4).addPlanet(new Planet("Rigel I"));
        galaxy.getSystems().get(4).addPlanet(new Planet("Rigel II"));
        galaxy.getSystems().get(6).addPlanet(new Planet("Mirage"));

        // Add blue ships to system
        galaxy.getSystems().get(0).addShip(new DreadnoughtUnit(blue));
        galaxy.getSystems().get(0).addShip(new DreadnoughtUnit(blue));
        galaxy.getSystems().get(0).addShip(new DestroyerUnit(blue));

        // Add red ships to system
        galaxy.getSystems().get(1).addShip(new CruiserUnit(red));
        galaxy.getSystems().get(1).addShip(new CruiserUnit(red));
        galaxy.getSystems().get(1).addShip(new CarrierUnit(red));

        // Test game.systems.HexaSystem list
        assertTrue(galaxy.systems.containsAll(sampleSystems));

        // Test individual systems
        assertTrue(galaxy.getSystems().get(0).getCardinal().equals("Center"));
        assertTrue(galaxy.getSystems().get(1).getCardinal().equals("North"));
        assertTrue(galaxy.getSystems().get(2).getCardinal().equals("North East"));
        assertTrue(galaxy.getSystems().get(3).getCardinal().equals("South East"));
        assertTrue(galaxy.getSystems().get(4).getCardinal().equals("South"));
        assertTrue(galaxy.getSystems().get(5).getCardinal().equals("South West"));
        assertTrue(galaxy.getSystems().get(6).getCardinal().equals("North West"));

        // Test individual planets
        assertTrue(galaxy.getPlanets().get(0).getName().equals("Mecatol Rex"));
        assertTrue(galaxy.getPlanets().get(1).getName().equals("Vega Minor"));
        assertTrue(galaxy.getPlanets().get(2).getName().equals("Vega Major"));
        assertTrue(galaxy.getPlanets().get(3).getName().equals("Industrex"));
        assertTrue(galaxy.getPlanets().get(4).getName().equals("Rigel I"));
        assertTrue(galaxy.getPlanets().get(5).getName().equals("Rigel II"));
        assertTrue(galaxy.getPlanets().get(6).getName().equals("Mirage"));

        // Test individual ships
        assertTrue(galaxy.getShips().get(0).getOwner().equals(blue));
        assertTrue(galaxy.getShips().get(1).getOwner().equals(blue));
        assertTrue(galaxy.getShips().get(2).getOwner().equals(blue));
        assertTrue(galaxy.getShips().get(3).getOwner().equals(red));
        assertTrue(galaxy.getShips().get(4).getOwner().equals(red));
        assertTrue(galaxy.getShips().get(5).getOwner().equals(red));
    }
}