import units.*;

import java.util.ArrayList;
import java.util.List;

public class Galaxy {
    private List<HexaSystem> systems;

    public Galaxy() {
        this.systems = new ArrayList<>();
    }

    public List<HexaSystem> getSystems() {
        return systems;
    }

    public List<Unit> getShips() {
        List<Unit> resolved = new ArrayList<>();
        List<Unit> ships;
        for(HexaSystem e : systems) {
            ships = e.getSystemShips();
            for (Unit u : ships) {
                resolved.add(u);
            }
        }
        return resolved;
    }

    public List<Planet> getPlanets() {
        List<Planet> resolved = new ArrayList();
        List<Planet> planets;
        for(HexaSystem e : systems) {
            planets = e.getSystemPlanets();
            for (Planet u : planets) {
                resolved.add(u);
            }
        }
        return resolved;
    }

    public void sampleGalaxy() {
        // Players
        Player blue = new Player("Crassus", "The Emirates of Hacan", "Blue");
        Player red = new Player("Pompey", "The Federation of Sol", "Red");

        // Systems
        HexaSystem center = new HexaSystem("Center");
        HexaSystem north = new HexaSystem("North");
        HexaSystem northEast = new HexaSystem("North East");
        HexaSystem southEast = new HexaSystem("South East");
        HexaSystem south = new HexaSystem("South");
        HexaSystem southWest = new HexaSystem("South West");
        HexaSystem northWest = new HexaSystem("North West");

        // Add planets
        center.addPlanet(new Planet("Mecatol Rex", 5));
        north.addPlanet(new Planet("Vega Minor", 1));
        north.addPlanet(new Planet("Vega Major", 3));
        southEast.addPlanet(new Planet("Industrex", 6));
        south.addPlanet(new Planet("Rigel I", 3));
        south.addPlanet(new Planet("Rigel II", 2));
        northWest.addPlanet(new Planet("Mirage", 4));

        // Generate ships - blue player
        DreadnoughtUnit dreadnought1 = new DreadnoughtUnit(blue);
        DreadnoughtUnit dreadnought2 = new DreadnoughtUnit(blue);
        DestroyerUnit destroyer = new DestroyerUnit(blue);

        // Generate ships - red player
        CruiserUnit cruiser1 = new CruiserUnit(red);
        CruiserUnit cruiser2 = new CruiserUnit(red);
        CarrierUnit carrier = new CarrierUnit(red);

        // Add blue ships to system
        center.addShip(dreadnought1);
        center.addShip(dreadnought2);
        center.addShip(destroyer);

        // Add red ships to system
        north.addShip(cruiser1);
        north.addShip(cruiser2);
        north.addShip(carrier);
    }
}
