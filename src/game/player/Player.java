/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.player;

import game.galaxy.Galaxy;
import game.planets.Planet;
import game.units.Unit;

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

        // assign player color upon creation
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

        // sort player units that are in combat
        // firstly sort by resourceCost, then combatValue, to get the list primarily sorted by combatValue
        Comparator<Unit> sortByCombatVal = Comparator.comparing(Unit::getCombatValue);
        Comparator<Unit> sortByResourceCost = Comparator.comparing(Unit::getResourceCost);
        shipsInCombat.sort(sortByResourceCost);
        shipsInCombat.sort(sortByCombatVal);

        return shipsInCombat;
    }

    public void clearShipsInCombat() {
        this.shipsInCombat.clear();
    }

    private String setPlayerColor(Galaxy galaxy) {
        String[] colors = {"Green", "Red", "Yellow", "Purple", "Black", "Blue"};

        // as players are created, amountOfPlayers will increase to ensure a color isn't assigned more than once
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
        List<Unit> playerShipsSorted = this.getShips();

        // sort player units firstly by resourceCost, then combatValue, to get the list primarily sorted by combatValue
        Comparator<Unit> sortByCombatVal = Comparator.comparing(Unit::getCombatValue);
        Comparator<Unit> sortByResourceCost = Comparator.comparing(Unit::getResourceCost);
        playerShipsSorted.sort(sortByResourceCost);
        playerShipsSorted.sort(sortByCombatVal);


        return playerShipsSorted;
    }
}
