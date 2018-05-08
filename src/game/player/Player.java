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
    private List<Unit> shipsInCombat;

    public Player(String name, String race, Galaxy galaxy) {
        this.name = name;
        this.race = race;
        this.color = setPlayerColor(galaxy);
        this.ships = new ArrayList<>();
        this.controlledPlanets = new ArrayList<>();
        this.shipsInCombat = new ArrayList<>();
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

    public List<Unit> getShipsInCombatSorted() {
        Comparator<Unit> sortResourceCost = Comparator.comparing(Unit::getResourceCost);
        shipsInCombat.sort(sortResourceCost.thenComparing(Unit::getCombatValue));

        return shipsInCombat;
    }

    public void clearShipsInCombat() {
        this.shipsInCombat.clear();
    }

    private String setPlayerColor(Galaxy galaxy) {
        String[] colors = {"Green", "Red", "Yellow", "Purple", "Black", "Blue"};
        int amountOfPlayers = galaxy.getPlayers().size();
        return colors[amountOfPlayers];
    }

    public void addShipInCombat(Unit e) {
        this.shipsInCombat.add(e);
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
        Comparator<Unit> sortResourceCost = Comparator.comparing(Unit::getResourceCost);
        playerUnits.sort(sortResourceCost.thenComparing(Unit::getCombatValue));

        System.out.println(playerUnits.toString());

        return playerUnits;
    }
}
