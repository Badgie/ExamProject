/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.systems;

import game.exceptions.SystemHasIncorrectNeighborsException;

public class HexaSystemPositionsCheckIfCorrect {

    public boolean checkIfNeighborsMatch(HexaSystem system) throws SystemHasIncorrectNeighborsException {

        // choose case based on cardinal direction of input system
        switch(system.getCardinal()) {
            case "Center":
                if(checkCenterNeighbors(system)) break;
                else throw new SystemHasIncorrectNeighborsException(system.getCardinal());
            case "North":
                if(checkNorthNeighbors(system)) break;
                else throw new SystemHasIncorrectNeighborsException(system.getCardinal());
            case "NorthEast":
                if(checkNorthEastNeighbors(system)) break;
                else throw new SystemHasIncorrectNeighborsException(system.getCardinal());
            case "NorthWest":
                if(checkNorthWestNeighbors(system)) break;
                else throw new SystemHasIncorrectNeighborsException(system.getCardinal());
            case "South":
                if(checkSouthNeighbors(system)) break;
                else throw new SystemHasIncorrectNeighborsException(system.getCardinal());
            case "SouthEast":
                if(checkSouthEastNeighbors(system)) break;
                else throw new SystemHasIncorrectNeighborsException(system.getCardinal());
            case "SouthWest":
                if(checkSouthWestNeighbors(system)) break;
                else throw new SystemHasIncorrectNeighborsException(system.getCardinal());
            default:
                throw new SystemHasIncorrectNeighborsException("FATAL", "Reached an unknown system.");
        }
        return true;
    }

    private boolean checkCenterNeighbors(HexaSystem system) {

        // for each system in input systems neighbors
        for(HexaSystem e : system.getNeighbors()) {

            // if system cardinal is center, return false, else return true
            if(e.getCardinal().equals("Center")) {
                return false;
            }
        }
        return true;
    }

    private boolean checkNorthNeighbors(HexaSystem system) {
        int amountOfNeighbors = 0;

        // for each system in input systems neighbors
        for(HexaSystem e : system.getNeighbors()) {

            // if system cardinal matches prerequisites for North system, increment amountOfNeighbors
            if(e.getCardinal().equals("NorthEast") || e.getCardinal().equals("NorthWest")
                    || e.getCardinal().equals("Center")) {
                amountOfNeighbors++;
            }
        }

        // if amountOfNeighbors is the same size as neighbors in input system, return true, else return false
        return system.getNeighbors().size() == amountOfNeighbors;
    }

    private boolean checkNorthEastNeighbors(HexaSystem system) {
        int amountOfNeighbors = 0;

        // for each system in input systems neighbors
        for(HexaSystem e : system.getNeighbors()) {

            // if system cardinal matches prerequisites for NorthEast system, increment amountOfNeighbors
            if(e.getCardinal().equals("North") || e.getCardinal().equals("SouthEast")
                    || e.getCardinal().equals("Center")) {
                amountOfNeighbors++;
            }
        }

        // if amountOfNeighbors is the same size as neighbors in input system, return true, else return false
        return system.getNeighbors().size() == amountOfNeighbors;
    }

    private boolean checkNorthWestNeighbors(HexaSystem system) {
        int amountOfNeighbors = 0;

        // for each system in input systems neighbors
        for(HexaSystem e : system.getNeighbors()) {

            // if system cardinal matches prerequisites for NorthWest system, increment amountOfNeighbors
            if(e.getCardinal().equals("North") || e.getCardinal().equals("SouthWest")
                    || e.getCardinal().equals("Center")) {
                amountOfNeighbors++;
            }
        }

        // if amountOfNeighbors is the same size as neighbors in input system, return true, else return false
        return system.getNeighbors().size() == amountOfNeighbors;
    }

    private boolean checkSouthNeighbors(HexaSystem system) {
        int amountOfNeighbors = 0;

        // for each system in input systems neighbors
        for(HexaSystem e : system.getNeighbors()) {

            // if system cardinal matches prerequisites for South system, increment amountOfNeighbors
            if(e.getCardinal().equals("SouthEast") || e.getCardinal().equals("SouthWest")
                    || e.getCardinal().equals("Center")) {
                amountOfNeighbors++;
            }
        }

        // if amountOfNeighbors is the same size as neighbors in input system, return true, else return false
        return system.getNeighbors().size() == amountOfNeighbors;
    }

    private boolean checkSouthEastNeighbors(HexaSystem system) {
        int amountOfNeighbors = 0;

        // for each system in input systems neighbors
        for(HexaSystem e : system.getNeighbors()) {

            // if system cardinal matches prerequisites for SouthEast system, increment amountOfNeighbors
            if(e.getCardinal().equals("South") || e.getCardinal().equals("NorthEast")
                    || e.getCardinal().equals("Center")) {
                amountOfNeighbors++;
            }
        }

        // if amountOfNeighbors is the same size as neighbors in input system, return true, else return false
        return system.getNeighbors().size() == amountOfNeighbors;
    }

    private boolean checkSouthWestNeighbors(HexaSystem system) {
        int amountOfNeighbors = 0;

        // for each system in input systems neighbors
        for(HexaSystem e : system.getNeighbors()) {

            // if system cardinal matches prerequisites for SouthWest system, increment amountOfNeighbors
            if(e.getCardinal().equals("South") || e.getCardinal().equals("NorthWest")
                    || e.getCardinal().equals("Center")) {
                amountOfNeighbors++;
            }
        }

        // if amountOfNeighbors is the same size as neighbors in input system, return true, else return false
        return system.getNeighbors().size() == amountOfNeighbors;
    }
}
