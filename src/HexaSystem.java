import exceptions.PrintException;
import units.Unit;

import java.util.ArrayList;
import java.util.List;

// Named HexaSystem to avoid clashes with System method
public class HexaSystem {

    private String cardinal;
    private List<HexaSystem> neighbors;
    private List<Planet> planets;
    private List<Unit> ships;

    public HexaSystem(String cardinal) {
        this.cardinal = cardinal;
        this.neighbors = new ArrayList<>();
        this.planets = new ArrayList<>();
        this.ships = new ArrayList<>();
    }

    public String getCardinal() {
        return cardinal;
    }

    public List<HexaSystem> getNeighbors() {
        return neighbors;
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

    public void addNeighbor(HexaSystem o) {
        neighbors.add(o);
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

    private HexaSystem findNeighbor(Galaxy galaxy, String cardinality) throws PrintException {
        boolean verdict = false;
        for(HexaSystem e : galaxy.getSystems()) {
            if(e.getCardinal().equals(cardinality)) {
                verdict = true;
            }
        }
        HexaSystem neighbor;
        if(verdict) {
            neighbor = new HexaSystem(cardinality);
        } else {
            throw new PrintException("ERROR: A system is missing.");
        }
        return neighbor;
    }

    public void setNeighbors(Galaxy galaxy, HexaSystem system) {
        switch(system.getCardinal()) {
            case "Center":
                try {
                    system.addNeighbor(findNeighbor(galaxy, "North"));
                    system.addNeighbor(findNeighbor(galaxy, "North East"));
                    system.addNeighbor(findNeighbor(galaxy, "North West"));
                    system.addNeighbor(findNeighbor(galaxy, "South"));
                    system.addNeighbor(findNeighbor(galaxy, "South East"));
                    system.addNeighbor(findNeighbor(galaxy, "South West"));
                } catch(PrintException e) {
                    e.getMessage();
                }
                break;

            case "North":
                try {
                    system.addNeighbor(findNeighbor(galaxy, "Center"));
                    system.addNeighbor(findNeighbor(galaxy, "North East"));
                    system.addNeighbor(findNeighbor(galaxy, "North West"));
                } catch(PrintException e) {
                    e.getMessage();
                }
                break;

            case "North East":
                try {
                    system.addNeighbor(findNeighbor(galaxy, "Center"));
                    system.addNeighbor(findNeighbor(galaxy, "North"));
                    system.addNeighbor(findNeighbor(galaxy, "South East"));
                } catch(PrintException e) {
                    e.getMessage();
                }
                break;

            case "North West":
                try {
                    system.addNeighbor(findNeighbor(galaxy, "Center"));
                    system.addNeighbor(findNeighbor(galaxy, "North"));
                    system.addNeighbor(findNeighbor(galaxy, "South West"));
                } catch(PrintException e) {
                    e.getMessage();
                }
                break;

            case "South":
                try {
                    system.addNeighbor(findNeighbor(galaxy, "Center"));
                    system.addNeighbor(findNeighbor(galaxy, "South East"));
                    system.addNeighbor(findNeighbor(galaxy, "South West"));
                } catch(PrintException e) {
                    e.getMessage();
                }
                break;
            case "South East":
                try {
                    system.addNeighbor(findNeighbor(galaxy, "Center"));
                    system.addNeighbor(findNeighbor(galaxy, "South"));
                    system.addNeighbor(findNeighbor(galaxy, "North East"));
                } catch(PrintException e) {
                    e.getMessage();
                }
                break;

            case "South West":
                try {
                    system.addNeighbor(findNeighbor(galaxy, "Center"));
                    system.addNeighbor(findNeighbor(galaxy, "South"));
                    system.addNeighbor(findNeighbor(galaxy, "North West"));
                } catch(PrintException e) {
                    e.getMessage();
                }
                break;
        }
    }
}
