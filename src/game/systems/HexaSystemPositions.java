package game.systems;

import game.galaxy.Galaxy;

import java.util.ArrayList;
import java.util.List;

public class HexaSystemPositions extends HexaSystemPositionsCheckIfCorrect {

    public void setNeighborsInSystems(Galaxy galaxy, HexaSystem system) {
        switch (system.getCardinal()) {
            case "Center":
                setNeighborsCenter(galaxy, system);
                break;
            case "North":
                setNeighborsNorth(galaxy, system);
                break;
            case "NorthEast":
                setNeighborsNorthEast(galaxy, system);
                break;
            case "NorthWest":
                setNeighborsNorthWest(galaxy, system);
                break;
            case "South":
                setNeighborsSouth(galaxy, system);
                break;
            case "SouthEast":
                setNeighborsSouthEast(galaxy, system);
                break;
            case "SouthWest":
                setNeighborsSouthWest(galaxy, system);
                break;
        }
    }

    private void setNeighborsCenter(Galaxy galaxy, HexaSystem system) {
        for(HexaSystem e : galaxy.getSystems()) {
            if(e != system) {
                system.addNeighbor(e);
            }
        }
    }

    private void setNeighborsNorth(Galaxy galaxy, HexaSystem system) {
        addCenterSystemAsNeighbor(galaxy, system);
        for(HexaSystem e : galaxy.getSystems()) {
            if(e.getCardinal().equals("NorthEast") || e.getCardinal().equals("NorthWest")) {
                system.addNeighbor(e);
            }
        }
    }

    private void setNeighborsNorthEast(Galaxy galaxy, HexaSystem system) {
        addCenterSystemAsNeighbor(galaxy, system);
        for (HexaSystem e : galaxy.getSystems()) {
            if (e.getCardinal().equals("North") || e.getCardinal().equals("SouthEast")) {
                system.addNeighbor(e);
            }
        }
    }

    private void setNeighborsNorthWest(Galaxy galaxy, HexaSystem system){
        addCenterSystemAsNeighbor(galaxy, system);
        for(HexaSystem e : galaxy.getSystems()) {
            if(e.getCardinal().equals("North") || e.getCardinal().equals("SouthWest")) {
                system.addNeighbor(e);
            }
        }
    }


    private void setNeighborsSouth(Galaxy galaxy, HexaSystem system) {
        addCenterSystemAsNeighbor(galaxy, system);
        for(HexaSystem e : galaxy.getSystems()) {
            if (e.getCardinal().equals("SouthEast") || e.getCardinal().equals("SouthWest")) {
                system.addNeighbor(e);
            }
        }
    }

    private void setNeighborsSouthEast(Galaxy galaxy, HexaSystem system) {
        addCenterSystemAsNeighbor(galaxy, system);
        for(HexaSystem e : galaxy.getSystems()) {
            if (e.getCardinal().equals("South") || e.getCardinal().equals("NorthEast")) {
                system.addNeighbor(e);
            }
        }
    }

    private void setNeighborsSouthWest(Galaxy galaxy, HexaSystem system) {
        addCenterSystemAsNeighbor(galaxy, system);
        for(HexaSystem e : galaxy.getSystems()) {
            if (e.getCardinal().equals("South") || e.getCardinal().equals("NorthWest")) {
                system.addNeighbor(e);
            }
        }
    }

    private void addCenterSystemAsNeighbor(Galaxy galaxy, HexaSystem system) {
        for(HexaSystem e : galaxy.getSystems()) {
            if(e.getCardinal().equals("Center")) {
                system.addNeighbor(e);
            }
        }
    }
}
