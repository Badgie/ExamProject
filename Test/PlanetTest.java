import game.planets.Planet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    @Test
    void testPlanet() {
        Planet planet = new Planet("Planet on a cob");
        assertTrue(planet.getName().equals("Planet on a cob"));
        assertTrue(planet.getResourceProduction() <= 6 && planet.getResourceProduction() >= 0);
    }
}