package game.systems;

import game.galaxy.Galaxy;

public class HexaSystemPositionsCheckIfCorrect {

    public boolean checkIfNeighborsMatch(HexaSystem system) {
        switch(system.getCardinal()) {
            case "Center":
                return checkCenterNeighbors(system);
            case "North":
                return (checkNorthNeighbors(system) && checkIfCenterIsNeighbor(system));
            case "NorthEast":
                return (checkNorthEastNeighbors(system) && checkIfCenterIsNeighbor(system));
            case "NorthWest":
                return (checkNorthWestNeighbors(system) && checkIfCenterIsNeighbor(system));
            case "South":
                return (checkSouthNeighbors(system) && checkIfCenterIsNeighbor(system));
            case "SouthEast":
                return (checkSouthEastNeighbors(system) && checkIfCenterIsNeighbor(system));
            case "SouthWest":
                return (checkSouthWestNeighbors(system) && checkIfCenterIsNeighbor(system));
            default:
                return false;
        }
    }

    private boolean checkCenterNeighbors(HexaSystem system) {
        for(HexaSystem e : system.getNeighbors()) {
            if(e.getCardinal().equals("Center")) {
                return false;
            }
        }
        return system.getNeighbors().size() == 6;
    }

    private boolean checkNorthNeighbors(HexaSystem system) {
        int amountOfNeighbors = 0;
        for(HexaSystem e : system.getNeighbors()) {
            if(e.getCardinal().equals("NorthEast") || e.getCardinal().equals("NorthWest")) {
                amountOfNeighbors++;
            }
        }
        return system.getNeighbors().size() == amountOfNeighbors;
    }

    private boolean checkNorthEastNeighbors(HexaSystem system) {
        int amountOfNeighbors = 0;
        for(HexaSystem e : system.getNeighbors()) {
            if(e.getCardinal().equals("North") || e.getCardinal().equals("SouthEast")) {
                amountOfNeighbors++;
            }
        }
        return system.getNeighbors().size() == amountOfNeighbors;
    }

    private boolean checkNorthWestNeighbors(HexaSystem system) {
        int amountOfNeighbors = 0;
        for(HexaSystem e : system.getNeighbors()) {
            if(e.getCardinal().equals("North") || e.getCardinal().equals("SouthWest")) {
                amountOfNeighbors++;
            }
        }
        return system.getNeighbors().size() == amountOfNeighbors;
    }

    private boolean checkSouthNeighbors(HexaSystem system) {
        int amountOfNeighbors = 0;
        for(HexaSystem e : system.getNeighbors()) {
            if(e.getCardinal().equals("SouthEast") || e.getCardinal().equals("SouthWest")) {
                amountOfNeighbors++;
            }
        }
        return system.getNeighbors().size() == amountOfNeighbors;
    }

    private boolean checkSouthEastNeighbors(HexaSystem system) {
        int amountOfNeighbors = 0;
        for(HexaSystem e : system.getNeighbors()) {
            if(e.getCardinal().equals("South") || e.getCardinal().equals("NorthEast")) {
                amountOfNeighbors++;
            }
        }
        return system.getNeighbors().size() == amountOfNeighbors;
    }

    private boolean checkSouthWestNeighbors(HexaSystem system) {
        int amountOfNeighbors = 0;
        for(HexaSystem e : system.getNeighbors()) {
            if(e.getCardinal().equals("South") || e.getCardinal().equals("NorthWest")) {
                amountOfNeighbors++;
            }
        }
        return system.getNeighbors().size() == amountOfNeighbors;
    }

    private boolean checkIfCenterIsNeighbor(HexaSystem system) {
        for(HexaSystem e : system.getNeighbors()) {
            if(e.getCardinal().equals("Center")) {
                return true;
            }
        }
        return false;
    }
}
