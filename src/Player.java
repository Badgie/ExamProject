import units.Unit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Player {

    private String name;
    private String race;
    private String color;
    private List<Unit> ships;

    public Player(String name, String race, String color) {
        this.name = name;
        this.race = race;
        this.color = color;
        this.ships = new ArrayList<>();
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
    public List<Unit> getPlayerShipsSorted(Player player) {
        List<Unit> playerUnits = new ArrayList<>(player.getShips());
        Comparator<Unit> sortByCombatVal = Comparator.comparing(Unit::getCombatValue);
        playerUnits.sort(sortByCombatVal.thenComparing(Unit::getResourceCost));

        /* System.out.println(String.format("%-8s %-15s %-15s %-15s %-15s %s", "Player:", "Unit",
                "Combat Value", "Resource Cost", "Movement Speed", "Capacity"));
        System.out.println(String.format("%-24s %-15s %-15s %-15s %s", "         Dreadnought", "5", "2",
                                        "5", "0")); */
        return playerUnits;
    }

    // problem 11
    public void createPlanetaryControlFile() {

    }

    public void setPlanetaryControl(HexaSystem system, Player player) {
        List<Unit> otherShipsInSystem = new ArrayList<>(system.getSystemShips());
        otherShipsInSystem.removeAll(player.getShips());
        for(Unit e : player.getShips()) {
            if(system.getSystemShips().contains(e) &&
                    otherShipsInSystem.size() == 0) {
                for(Planet p : system.getSystemPlanets()) {
                    p.setPlayerInControl(player);
                }
            }
        }
    }
}
