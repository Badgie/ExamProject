/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.galaxy;

import game.exceptions.*;
import game.planets.Planet;
import game.systems.HexaSystem;

import java.util.ArrayList;
import java.util.HashSet;

public class GalaxyLegalityCheck {

    // Problem 9
    public boolean checkIfGalaxyIsLegal(Galaxy galaxy) throws GalaxyNotLegalException {
        boolean condOne = false;
        boolean condTwo = false;
        boolean condThree = false;

        // check if center system is legal
        try {
            condOne = checkCenterGalaxyPlanets(galaxy);
        } catch (CenterSystemNotLegalException x) {

            // if exception is thrown, catch and print stack trace
            x.printStackTrace();
        }

        // check if duplicate planets exist
        try {
            condTwo = checkForDuplicatePlanets(galaxy);
        } catch (PlanetExistsMoreThanOnceInGalaxyException x) {

            // if exception is thrown, catch and print stack trace
            x.printStackTrace();
        }

        // check if a system contains more than three planets
        try {
            condThree = checkForMoreThanThreePlanets(galaxy);
        } catch (SystemContainsMoreThanThreePlanetsException x) {

            // if exception is thrown, catch and print stack trace
            x.printStackTrace();
        }

        boolean condFour = checkCardinalDirections(galaxy);

        // if all four conditions are true, return true, else throw exception
        if (condOne && condTwo && condThree && condFour) {
            return true;
        } else {
            throw new GalaxyNotLegalException();
        }
    }

    // first criteria
    private boolean checkCenterGalaxyPlanets(Galaxy galaxy) throws CenterSystemNotLegalException {

        // for each system in galaxy
        for (HexaSystem e : galaxy.getSystems()) {

            // if system is center, check each planet
            if (e.getCardinal().equals("Center")) {
                for (Planet p : e.getSystemPlanets())

                    // if planet name is Mecatol Rex, continue, else throw exception
                    if (p.getName().equals("Mecatol Rex") && getCenterSystemPlanets(galaxy).size() == 1) {
                    } else {
                        throw new CenterSystemNotLegalException();
                    }
            }
        }
        return true;
    }

    // second criteria
    private boolean checkForDuplicatePlanets(Galaxy galaxy) throws PlanetExistsMoreThanOnceInGalaxyException {

        // if amount of planets in galaxy equals filtered planets, return true, else throw exception
        if (galaxy.getPlanets().size() == getGalaxyPlanetNamesFiltered(galaxy).size()) {
            return true;
        } else {
            throw new PlanetExistsMoreThanOnceInGalaxyException();
        }
    }

    // third criteria
    private boolean checkForMoreThanThreePlanets(Galaxy galaxy) throws SystemContainsMoreThanThreePlanetsException {

        // for each system in galaxy
        for (HexaSystem e : galaxy.getSystems()) {

            // if amount of planets in system is three or less, continue, else throw exception
            if (e.getSystemPlanets().size() <= 3) {
            } else {
                throw new SystemContainsMoreThanThreePlanetsException();
            }
        }
        return true;
    }

    // fourth criteria
    private boolean checkCardinalDirections(Galaxy galaxy) {

        // for each system in galaxy
        for (HexaSystem e : galaxy.getSystems()) {

            // try checking if neighbors match
            try {
                e.checkIfNeighborsMatch(e);
            } catch (SystemHasIncorrectNeighborsException x) {

                // if exception is thrown, catch and print stack trace
                x.printStackTrace();
            }
        }
        return true;
    }

    // helpermethods
    private HashSet<String> getGalaxyPlanetNamesFiltered(Galaxy galaxy) {

        // hash set used to avoid duplicate names
        HashSet<String> array = new HashSet<>();

        // for each planet in galaxy, add name to hash set
        for (Planet e : galaxy.getPlanets()) {
            array.add(e.getName());
        }
        return array;
    }

    private ArrayList<String> getSystemPlanetNames(HexaSystem system) {
        ArrayList<String> array = new ArrayList<>();

        // for each planet in system, add name to list
        for (Planet e : system.getSystemPlanets()) {
            array.add(e.getName());
        }
        return array;
    }

    private ArrayList<Planet> getCenterSystemPlanets(Galaxy galaxy) {
        ArrayList<Planet> systemPlanets = new ArrayList<>();

        // loop through systems
        for (int i = 0; i < galaxy.getSystems().size(); i++) {

            // if system is center system, add all planets to list
            if (galaxy.getSystems().get(i).getCardinal().equals("Center")) {
                systemPlanets.addAll(galaxy.getSystems().get(i).getSystemPlanets());
            }
        }
        return systemPlanets;
    }
}
