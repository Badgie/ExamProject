/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.planets;

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