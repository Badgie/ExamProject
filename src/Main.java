/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

import game.galaxy.Galaxy;
import game.systems.HexaSystem;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Galaxy galaxy = new Galaxy();
        galaxy.constructGalaxy(galaxy);
        System.out.println("Galaxy was successfully constructed.");
        System.out.println("Systems: " + galaxy.getSystems().size());
        System.out.println("Planets: " + galaxy.getPlanets().size());
        System.out.println("Players: " + galaxy.getPlayers().size());
        System.out.println("Ships: " + galaxy.getShips().size());
        System.out.println("Checking if combat is necessary...");
        galaxy.checkIfCombatIsNecessaryInGalaxy();
        System.out.println("Creating planetary control file...");
        try {
            galaxy.createPlanetaryControlFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }
}
