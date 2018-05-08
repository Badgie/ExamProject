package game.systems;

import game.exceptions.PrintException;
import game.galaxy.Galaxy;
import game.planets.Planet;
import game.player.Player;
import game.units.CarrierUnit;
import game.units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Named HexaSystem to avoid clashes with System method
public class HexaSystem extends HexaSystemPositions {

    private String cardinal;
    private List<HexaSystem> neighbors;
    private List<Planet> planets;
    private List<Unit> ships;

    public HexaSystem(Galaxy galaxy) {
        this.cardinal = setNewCardinal(galaxy);
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

    public List<Unit> getSystemShips() {
        return ships;
    }

    public List<Planet> getSystemPlanets() {
        return planets;
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

    private String setNewCardinal(Galaxy galaxy) {
        String[] cardinalDirections = {"Center", "North", "NorthEast", "NorthWest",
                "South", "SouthEast", "SouthWest"};
        int amountOfSystems = galaxy.getSystems().size();
        return cardinalDirections[amountOfSystems];
    }

    @Override
    public String toString() {
        return "HexaSystem{" + "cardinal='" + cardinal + '\'' + ",\n planets=" + planets + ",\n ships=" + ships + "}\n";
    }

    public Player concludeCombat(Player playerOne, Player playerTwo) {
        Player winner;
        setPlayerShipsInSystem(playerOne);
        setPlayerShipsInSystem(playerTwo);
        while(true) {
            if(playerTwo.getShipsInCombatSorted().size() != 0) {
                calculateHitsDoneByPlayerOne(playerOne, playerTwo);
            } else {
                winner = playerOne;
                break;
            }
            if(playerOne.getShipsInCombatSorted().size() != 0) {
                calculateHitsDoneByPlayerTwo(playerOne, playerTwo);
            } else {
                winner = playerTwo;
                break;
            }
            System.out.println("One: " + playerOne.getShipsInCombatSorted().size() + " Two: " + playerTwo.getShipsInCombatSorted().size());
        }

        playerOne.clearShipsInCombat();
        playerTwo.clearShipsInCombat();

        return winner;
    }

    public void calculateHitsDoneByPlayerOne(Player playerOne, Player playerTwo) {
        Random rand = new Random();

        if(playerTwo.getShipsInCombatSorted().size() > 0){
            for (Unit e : playerOne.getShipsInCombatSorted()) {
                int diceRoll = rand.nextInt(10) + 1;
                if (diceRoll >= e.getCombatValue()) {
                    playerTwo.getShipsInCombatSorted().remove(getPlayerWorstShipInSystem(playerTwo));
                }
            }
        }
    }

    public void calculateHitsDoneByPlayerTwo(Player playerOne, Player playerTwo) {
        Random rand = new Random();

        if(playerOne.getShipsInCombatSorted().size() > 0) {
            for (Unit e : playerTwo.getShipsInCombatSorted()) {
                int diceRoll = rand.nextInt(10) + 1;
                if (diceRoll >= e.getCombatValue()) {
                    playerOne.getShipsInCombatSorted().remove(getPlayerWorstShipInSystem(playerOne));
                }
            }
        }
    }

    public Unit getPlayerWorstShipInSystem(Player player) {
        return player.getShipsInCombatSorted().get(player.getShipsInCombatSorted().size() - 1);
    }

    public void setPlayerShipsInSystem(Player player) {
        for(Unit e : getSystemShips()) {
            if(e.getOwner().equals(player))
                player.addShipInCombat(e);
        }
    }

    public List<Player> getPlayersInSystem() {
        List<Player> playersInSystem = new ArrayList<>();
        for(Unit e : getSystemShips()) {
            if(!playersInSystem.contains(e.getOwner())) {
                playersInSystem.add(e.getOwner());
            }
        }
        return playersInSystem;
    }
}
