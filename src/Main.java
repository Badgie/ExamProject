// import game.units.*;

import game.galaxy.Galaxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {


        
        Galaxy galaxy = new Galaxy().constructGalaxy();
        System.out.println("Galaxy was succesfully constructed.");
        System.out.println("Systems: " + galaxy.getSystems().size());
        System.out.println("Planets: " + galaxy.getPlanets().size());
        System.out.println("Players: " + galaxy.getPlayers().size());
        System.out.println("Ships: " + galaxy.getShips().size());
        System.out.println("Checking if combat is necessary...");
        galaxy.checkIfCombatIsNecessaryInSystems();
        System.out.println("Creating planetary control file...");
        try {
            galaxy.createPlanetaryControlFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done!");
    }
}
