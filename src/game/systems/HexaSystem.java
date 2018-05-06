package game.systems;

import game.exceptions.PrintException;
import game.galaxy.Galaxy;
import game.planets.Planet;
import game.player.Player;
import game.units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        return "HexaSystem{" + "cardinal='" + cardinal + '\'' + ", neighbors=" + neighbors + ", planets=" + planets + ", ships=" + ships + '}';
    }

    private HexaSystem findNeighbor(Galaxy galaxy, String cardinality) throws PrintException {

        for(HexaSystem e : galaxy.getSystems()) {
            if(e.getCardinal().equals(cardinality)) {

            } else {
                throw new PrintException("ERROR: A system is missing.");
            }
        }

        return new HexaSystem(cardinality);
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



    public boolean checkIfMoreThanTwoPlayersExistInSystem() {
        if(getPlayersInSystem().size() > 1) {
            return true;
        } else {
            return false;
        }
    }

    public void concludeCombat(Player playerOne, Player playerTwo) {
        while(getPlayerShipsInSystem(playerOne).size() != 0 ||
                getPlayerShipsInSystem(playerTwo).size() != 0) {
            List<Unit> hitsByPlayerOne = calculateHitsDoneByPlayerOne(playerOne, playerTwo);
            List<Unit> hitsByPlayerTwo = calculateHitsDoneByPlayerTwo(playerOne, playerTwo);
            playerTwo.getShips().removeAll(hitsByPlayerOne);
            playerOne.getShips().removeAll(hitsByPlayerTwo);
        }
    }

    public List<Unit> calculateHitsDoneByPlayerOne(Player playerOne, Player playerTwo) {
        Random rand = new Random();
        List<Unit> playerTwoCasualties = new ArrayList<>();
        int decrement = 1;

        for(Unit e : getPlayerShipsInSystem(playerOne)) {
            int diceRoll = rand.nextInt(10) + 1;
            if (diceRoll >= e.getCombatValue()) {
                playerTwoCasualties.add(getPlayerWorstShipInSystem(playerTwo, decrement));
                decrement++;
            }
        }
        return playerTwoCasualties;
    }

    public List<Unit> calculateHitsDoneByPlayerTwo(Player playerOne, Player playerTwo) {
        Random rand = new Random();
        List<Unit> playerOneCasualties = new ArrayList<>();
        int decrement = 2;

        for(Unit e : getPlayerShipsInSystem(playerTwo)) {
            int diceRoll = rand.nextInt(10) + 1;
            if (diceRoll >= e.getCombatValue()) {
                playerOneCasualties.add(getPlayerWorstShipInSystem(playerOne, decrement));
                decrement++;
            }
        }
        return playerOneCasualties;
    }

    public Unit getPlayerWorstShipInSystem(Player player, int decrement) {
        List<Unit> playerShips = getPlayerShipsInSystem(player);
        int findIndex = 1;

        if(getPlayerShipsInSystem(player).size() > 1) {
            return playerShips.get(playerShips.size() - decrement);
        } else {
            return playerShips.get(playerShips.size() - findIndex);
        }
    }

    public List<Unit> getPlayerShipsInSystem(Player player) {
        List<Unit> playerShips = new ArrayList<>();

        for(Unit e : getSystemShips()) {
            if(e.getOwner().equals(player))
                playerShips.add(e);
        }
        return playerShips;
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
