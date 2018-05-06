// import game.units.*;

import game.galaxy.Galaxy;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        
        Galaxy galaxy = new Galaxy().constructGalaxy();
        System.out.println(galaxy.toString());
        galaxy.checkIfCombatIsNecessaryInSystems();
    }
}
