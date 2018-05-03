import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    @Test
    void testPlanet() {
        Planet planet = new Planet("Planet on a cob", 5);
        assertTrue(planet.getName().equals("Planet on a cob"));
        assertTrue(planet.getResourceProduction() == 5);
    }
}