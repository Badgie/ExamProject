import units.Unit;

import java.util.ArrayList;
import java.util.List;

public class HexaSystem {

    private String cardinal;
    private List<HexaSystem> neighbors;
    private List<Planet> planets;
    private List<Unit> ships;

    public HexaSystem(String cardinal/*, List<HexaSystem> neighbors, List<Planet> planets, List<Unit> ships*/) {
        this.cardinal = cardinal;
        this.neighbors = new ArrayList<>();
        this.planets = new ArrayList<>();
        this.ships = new ArrayList<>();
    }

    public void addShip(Unit o) {
        ships.add(o);
    }

    public void removeShip(Unit o) {
        ships.remove(o);
    }

    public void addPlanet(Planet o) {
        planets.add(o);
    }

    public List<Unit> getSystemShips() {
        return ships;
    }

    public List<Planet> getSystemPlanets() {
        return planets;
    }

    @Override
    public String toString() {
        return "HexaSystem{" + "ships=" + ships + '}';
    }
}
