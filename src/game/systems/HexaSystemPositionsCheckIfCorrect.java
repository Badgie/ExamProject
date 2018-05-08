package game.systems;

import game.exceptions.SystemHasIncorrectNeighborsException;
import game.galaxy.Galaxy;

public class HexaSystemPositionsCheckIfCorrect {

    public boolean checkIfNeighborsMatch(HexaSystem system) throws SystemHasIncorrectNeighborsException {
        switch(system.getCardinal()) {
            case "Center":
                if(checkCenterNeighbors(system)) return true;
                else throw new SystemHasIncorrectNeighborsException(system.getCardinal());
            case "North":
                if(checkNorthNeighbors(system) && checkIfCenterIsNeighbor(system)) return true;
                else throw new SystemHasIncorrectNeighborsException(system.getCardinal());
            case "NorthEast":
                if(checkNorthEastNeighbors(system) && checkIfCenterIsNeighbor(system)) return true;
                else throw new SystemHasIncorrectNeighborsException(system.getCardinal());
            case "NorthWest":
                if(checkNorthWestNeighbors(system) && checkIfCenterIsNeighbor(system)) return true;
                else throw new SystemHasIncorrectNeighborsException(system.getCardinal());
            case "South":
                if(checkSouthNeighbors(system) && checkIfCenterIsNeighbor(system)) return true;
                else throw new SystemHasIncorrectNeighborsException(system.getCardinal());
            case "SouthEast":
                if(checkSouthEastNeighbors(system) && checkIfCenterIsNeighbor(system)) return true;
                else throw new SystemHasIncorrectNeighborsException(system.getCardinal());
            case "SouthWest":
                if(checkSouthWestNeighbors(system) && checkIfCenterIsNeighbor(system)) return true;
                else throw new SystemHasIncorrectNeighborsException(system.getCardinal());
            default:
                throw new SystemHasIncorrectNeighborsException("FATAL", "Reached an unknown system.");
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
