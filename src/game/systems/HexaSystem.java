import exceptions.PrintException;
import units.Unit;

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

    private List<Unit> getPlayerShipsInSystem(Player player) {
        List<Unit> playerShips = new ArrayList<>();

        for(Unit e : this.getSystemShips()) {
            if(e.getOwner().equals(player))
                playerShips.add(e);
        }
        return playerShips;
    }

    @Override
    public String toString() {
        return "HexaSystem{" + "ships=" + ships + '}';
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

    private boolean checkIfTwoPlayersAreInTheSameSystem(HexaSystem system) {
        if()
    }

    private void doTheFightyThing(Player playerOne, Player playerTwo) {
        do {
            playerTwo.getShips().removeAll(calculateHitsDoneByPlayerOne(playerOne, playerTwo));
            playerOne.getShips().removeAll(calculateHitsDoneByPlayerTwo(playerOne, playerTwo));
        } while(playerOne.getShips().size() != 0 ||
                playerTwo.getShips().size() != 0);
    }

    private List<Unit> calculateHitsDoneByPlayerOne(Player playerOne, Player playerTwo, HexaSystem system) {
        Random rand = new Random();
        List<Unit> playerTwoCasualties = new ArrayList<>();

        for(Unit e : getPlayerShipsInSystem(playerOne)) {
            int diceRoll = rand.nextInt(10) + 1;
            if (diceRoll >= e.getCombatValue()) {
                playerTwoCasualties.add(getPlayerWorstShipInSystem(playerTwo));
            }
        }
        return playerTwoCasualties;
    }

    private List<Unit> calculateHitsDoneByPlayerTwo(Player playerOne, Player playerTwo) {
        Random rand = new Random();
        List<Unit> playerOneCasualties = new ArrayList<>();

        for(Unit e : getPlayerShipsInSystem(playerTwo)) {
            int diceRoll = rand.nextInt(10) + 1;
            if (diceRoll >= e.getCombatValue()) {
                playerOneCasualties.add(getPlayerWorstShipInSystem(playerOne));
            }
        }
        return playerOneCasualties;
    }

    private Unit getPlayerWorstShipInSystem(Player player) {
        return getPlayerShipsInSystem(player).get(getPlayerShipsInSystem(player).size() - 1);
    }
}
