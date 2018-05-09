/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.systems;

import game.galaxy.Galaxy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HexaSystemPositionsTest {

    @Test
    void testSetNeighbors() {
        Galaxy galaxy = new Galaxy();
        List<HexaSystem> systems = galaxy.getSystems();

        // add systems
        systems.add(new HexaSystem(galaxy)); // center = 0
        systems.add(new HexaSystem(galaxy)); // north = 1
        systems.add(new HexaSystem(galaxy)); // north east = 2
        systems.add(new HexaSystem(galaxy)); // north west = 3
        systems.add(new HexaSystem(galaxy)); // south = 4
        systems.add(new HexaSystem(galaxy)); // south east = 5
        systems.add(new HexaSystem(galaxy)); // south west = 6

        // initialize systems
        HexaSystem center = systems.get(0);
        HexaSystem north = systems.get(1);
        HexaSystem northEast = systems.get(2);
        HexaSystem northWest = systems.get(3);
        HexaSystem south = systems.get(4);
        HexaSystem southEast = systems.get(5);
        HexaSystem southWest = systems.get(6);

        // set neighbors
        center.setNeighborsInSystems(galaxy, center);
        north.setNeighborsInSystems(galaxy, north);
        northEast.setNeighborsInSystems(galaxy, northEast);
        northWest.setNeighborsInSystems(galaxy, northWest);
        south.setNeighborsInSystems(galaxy, south);
        southEast.setNeighborsInSystems(galaxy, southEast);
        southWest.setNeighborsInSystems(galaxy, southWest);

        // center
        assertTrue(center.getNeighbors().contains(north));
        assertTrue(center.getNeighbors().contains(northEast));
        assertTrue(center.getNeighbors().contains(northWest));
        assertTrue(center.getNeighbors().contains(south));
        assertTrue(center.getNeighbors().contains(southEast));
        assertTrue(center.getNeighbors().contains(southWest));

        // north
        assertTrue(north.getNeighbors().contains(center));
        assertTrue(north.getNeighbors().contains(northEast));
        assertTrue(north.getNeighbors().contains(northWest));

        // north east
        assertTrue(northEast.getNeighbors().contains(center));
        assertTrue(northEast.getNeighbors().contains(north));
        assertTrue(northEast.getNeighbors().contains(southEast));

        // north west
        assertTrue(northWest.getNeighbors().contains(center));
        assertTrue(northWest.getNeighbors().contains(north));
        assertTrue(northWest.getNeighbors().contains(southWest));

        // south
        assertTrue(south.getNeighbors().contains(center));
        assertTrue(south.getNeighbors().contains(southEast));
        assertTrue(south.getNeighbors().contains(southWest));

        // south east
        assertTrue(southEast.getNeighbors().contains(center));
        assertTrue(southEast.getNeighbors().contains(south));
        assertTrue(southEast.getNeighbors().contains(northEast));

        // south west
        assertTrue(southWest.getNeighbors().contains(center));
        assertTrue(southWest.getNeighbors().contains(south));
        assertTrue(southWest.getNeighbors().contains(northWest));
    }
}