/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.systems;

import game.galaxy.Galaxy;
import game.planets.Planet;
import game.player.Player;
import game.units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Named HexaSystem to avoid clashes with System.out
public class HexaSystem extends HexaSystemPositions {

    private String cardinal;
    private List<HexaSystem> neighbors;
    private List<Planet> planets;
    private List<Unit> ships;

    public HexaSystem(Galaxy galaxy) {

        // assign cardinal direction upon creation
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

        // as systems are created, amountOfSystems will change to ensure a system isn't created more than once
        int amountOfSystems = galaxy.getSystems().size();
        return cardinalDirections[amountOfSystems];
    }

    @Override
    public String toString() {
        return "HexaSystem{" + "cardinal='" + cardinal + '\'' + ", planets=" + planets + ", ships=" + ships + "}\n";
    }

    public Player concludeCombat(Player playerOne, Player playerTwo) {
        Player winner;

        // set each players shipsInCombat
        setPlayerShipsInSystem(playerOne);
        setPlayerShipsInSystem(playerTwo);

        while(true) {

            // if playerTwo has no more ships, set playerOne as winner and break loop
            if(playerTwo.getShipsInCombatSorted().size() > 0
                    && playerTwo.getShipsInCombatSorted() != null) {
                calculateHitsDoneByPlayerOne(playerOne, playerTwo);
            } else {
                winner = playerOne;
                break;
            }

            // if playerOne has no more ships, set playerTwo as winner and break loop
            if(playerOne.getShipsInCombatSorted().size() > 0
                    && playerTwo.getShipsInCombatSorted() != null) {
                calculateHitsDoneByPlayerTwo(playerOne, playerTwo);
            } else {
                winner = playerTwo;
                break;
            }
        }

        // clear each players shipsInCombat to ensure only ships in the given system are in the next fight
        playerOne.clearShipsInCombat();
        playerTwo.clearShipsInCombat();

        return winner;
    }

    private void calculateHitsDoneByPlayerOne(Player playerOne, Player playerTwo) {
        Random rand = new Random();

        // for each unit playerOne has in system, do a dice roll
        for (Unit e : playerOne.getShipsInCombatSorted()) {
            int diceRoll = rand.nextInt(10) + 1;

            // if diceRoll is higher than the unit's combat val and if playerTwo still has ships
            // remove playerOne's worst ship
            // ships and shipsInCombat are two different lists - remove from both
            if (diceRoll >= e.getCombatValue()
                    && playerTwo.getShipsInCombatSorted().size() > 0) {
                playerTwo.getShipsInCombatSorted().remove(getPlayerWorstShipInSystem(playerTwo));
                playerTwo.getShips().remove(getPlayerWorstShipInSystem(playerTwo));
            } else {
                break;
            }
        }
    }

    private void calculateHitsDoneByPlayerTwo(Player playerOne, Player playerTwo) {
        Random rand = new Random();

        // for each unit playerTwo has in system, do a dice roll
        for (Unit e : playerTwo.getShipsInCombatSorted()) {
            int diceRoll = rand.nextInt(10) + 1;

            // if diceRoll is higher than the unit's combat val and if playerOne still has ships
            // remove playerOne's worst ship
            // ships and shipsInCombat are two different lists - remove from both
            if (diceRoll >= e.getCombatValue()
                    && playerOne.getShipsInCombatSorted().size() > 0) {
                playerOne.getShipsInCombatSorted().remove(getPlayerWorstShipInSystem(playerOne));
                playerOne.getShips().remove(getPlayerWorstShipInSystem(playerOne));
            } else {
                break;
            }
        }
    }

    public Unit getPlayerWorstShipInSystem(Player player) {
        // returns the first index of the players sorted ships
        if(player.getShipsInCombatSorted().size() > 0) {
            return player.getShipsInCombatSorted().get(0);
        } else {
            return null;
        }
    }

    public void setPlayerShipsInSystem(Player player) {

        // for each unit in this system
        for(Unit e : this.ships) {

            // if the ship's owner matches input player, add to player.shipsInCombat
            if(e.getOwner().equals(player))
                player.addShipInCombat(e);
        }
    }

    public List<Player> getPlayersInSystem() {
        List<Player> playersInSystem = new ArrayList<>();

        // for each unit in this system
        for(Unit e : this.ships) {

            // if list playersInSystem does not contain the unit's owner, add the owner to list
            if(!playersInSystem.contains(e.getOwner())) {
                playersInSystem.add(e.getOwner());
            }
        }
        return playersInSystem;
    }
}
