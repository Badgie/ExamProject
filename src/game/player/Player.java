package game.player;

import game.galaxy.Galaxy;
import game.planets.Planet;
import game.systems.HexaSystem;
import game.units.Unit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Player {

    private String name;
    private String race;
    private String color;
    private List<Unit> ships;
    private List<Planet> controlledPlanets;

    public Player(String name, String race, String color) {
        this.name = name;
        this.race = race;
        this.color = color;
        this.ships = new ArrayList<>();
        this.controlledPlanets = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public String getColor() {
        return color;
    }

    public List<Unit> getShips() {
        return ships;
    }

    public List<Planet> getControlledPlanets() {
        return controlledPlanets;
    }

    public void addControlledPlanet(Planet o) {
        controlledPlanets.add(o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(race, player.race) && Objects.equals(color, player.color);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, race, color);
    }

    @Override
    public String toString() {
        return "Player{" + "name='" + name + '\'' + ", race='" + race + '\'' + ", color='" + color + '\'' + '}';
    }

    // problem 10
    public List<Unit> getPlayerShipsSortedHighToLow() {
        List<Unit> playerUnits = new ArrayList<>(this.ships);
        Comparator<Unit> sortByCombatVal = Comparator.comparing(Unit::getCombatValue);
        playerUnits.sort(sortByCombatVal.thenComparing(Unit::getResourceCost));

        return playerUnits;
    }

    public Unit getPlayerWorstShip() {
        Unit worstShip;
        worstShip = getPlayerShipsSortedHighToLow().get(
                getPlayerShipsSortedHighToLow().size() - 1);
        return worstShip;
    }

    // problem 11
    public void createPlanetaryControlFile(Galaxy galaxy) throws IOException {
        PrintWriter writer = new PrintWriter("planetary-control.txt", "UTF-8");

        // for every player in the galaxy, write headliner
        for(Player e : galaxy.getPlayers()) {
            writer.println(String.format("%-15s %s (%s)",
                    e.color + " Player:", e.name, e.race));

            // for every planet the player controls, write planet name
            for(Planet p : e.controlledPlanets) {
                writer.println(String.format("%15s %s", "", p.getName()));
            }

            // add newline once player no longer controls any planets
            writer.println("\n");
            writer.close();
        }
    }

    public void setPlanetaryControl(HexaSystem system, Player player) {
        List<Unit> otherShipsInSystem = new ArrayList<>(system.getSystemShips());
        otherShipsInSystem.removeAll(player.getShips());
        for(Unit e : player.getShips()) {
            if(system.getSystemShips().contains(e) &&
                    otherShipsInSystem.size() == 0) {
                for(Planet p : system.getSystemPlanets()) {
                    p.setPlayerInControl(player);
                    player.addControlledPlanet(p);
                }
            }
        }
    }
}
