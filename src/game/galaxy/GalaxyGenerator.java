/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.galaxy;

import game.exceptions.GalaxyNotLegalException;
import game.planets.Planet;
import game.player.Player;
import game.systems.HexaSystem;
import game.units.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GalaxyGenerator extends GalaxyLegalityCheck {

    // problem 12
    public Galaxy constructGalaxy(Galaxy galaxy) {
        // generate systems, planets and players(as well as ships)
        generateSystems(galaxy);
        generatePlanets(galaxy);
        generatePlayers(galaxy);

        // try galaxy legality check
        try {
            if (checkIfGalaxyIsLegal(galaxy)) ;
        } catch (GalaxyNotLegalException e) {

            // if exception is thrown, catch and print stack trace
            e.printStackTrace();
        }

        return galaxy;
    }

    // only run in generateGalaxy
    private void generateSystems(Galaxy galaxy) {
        int amountOfSystems = 7;

        // until i reaches amountOfSystems, create new system in galaxy
        for (int i = 0; i < amountOfSystems; i++) {
            galaxy.getSystems().add(new HexaSystem(galaxy));
        }

        // for each system in galaxy, set neighbors
        for (HexaSystem e : galaxy.getSystems()) {
            e.setNeighborsInSystems(galaxy, e);

        }
    }

    // only run in generateGalaxy
    private void generatePlanets(Galaxy galaxy) {
        Random rand = new Random();
        int lengthOfPlanetNamesTxt = 57;

        // set as 4 to get 0...3
        int maxAmountOfPlanets = 4;

        // try generating planets
        try {
            List<String> planetNames = new ArrayList<>(getPlanetNamesFromFile());

            // for each system in galaxy
            for (HexaSystem e : galaxy.getSystems()) {

                // if system cardinal is center, add Mecatol Rex
                // else add between zero and three planets to system
                if (e.getCardinal().equals("Center")) {
                    e.addPlanet(new Planet("Mecatol Rex"));
                } else {
                    for (int i = 0; i < rand.nextInt(maxAmountOfPlanets); i++) {
                        e.addPlanet(new Planet(planetNames.get(rand.nextInt(lengthOfPlanetNamesTxt))));
                    }
                }
            }
        } catch (IOException e) {

            // if exception is thrown, catch and print stack trace
            e.printStackTrace();
        }
    }

    // only run in generateGalaxy
    private void generatePlayers(Galaxy galaxy) {
        Random rand = new Random();

        // try generating players
        try {

            // lists included in try, as playerNames and playerRaces are read from .txt file
            List<String> playerNames = new ArrayList<>(getPlayerNamesFromFile());
            List<String> playerRaces = new ArrayList<>(getPlayerRacesFromFile());

            // generate random amount of players between 2...6 and add to galaxy
            // players get random name and race from .txt files
            for (int i = 0; i < rand.nextInt(5) + 2; i++) {
                galaxy.getPlayers().add(new Player(playerNames.get(rand.nextInt(playerNames.size())), playerRaces.get(rand.nextInt(playerRaces.size())), galaxy));
            }

            // for each player, generate ships and add to random systems
            for (Player e : galaxy.getPlayers()) {
                generateShips(e);
                addPlayerShipsToRandomSystems(e, galaxy);
            }
        } catch (IOException e) {

            // if exception is thrown, catch and print stack trace
            e.printStackTrace();
        }

    }

    // only run in generatePlayers
    private void generateShips(Player player) {

        // upper boundary for amount of ships a player can have
        int maxShips = 8;
        Random rand = new Random();

        // generate a random ship until upper boundary is reached
        for (int i = 0; i < maxShips; i++) {
            int randomShip = rand.nextInt(4);

            // case based on random number 0...3
            switch (randomShip) {
                case 0:
                    player.getShips().add(new DreadnoughtUnit(player));
                    break;
                case 1:
                    player.getShips().add(new DestroyerUnit(player));
                    break;
                case 2:
                    player.getShips().add(new CruiserUnit(player));
                    break;
                case 3:
                    player.getShips().add(new CarrierUnit(player));
                    break;
            }
        }
    }

    // only run in generatePlayers
    private void addPlayerShipsToRandomSystems(Player player, Galaxy galaxy) {
        Random rand = new Random();
        int systemBoundary = 7;

        // for each unit a player owns
        for (Unit e : player.getShips()) {

            // get random system
            int randomNumber = rand.nextInt(systemBoundary);
            HexaSystem randomSystem = galaxy.getSystems().get(randomNumber);

            // if random system has less than two players, add unit
            // else, for each system player is in, add unit
            if (randomSystem.getPlayersInSystem().size() < 2 || randomSystem.getPlayersInSystem().contains(player)) {
                randomSystem.addShip(e);
            } else {
                for (HexaSystem s : galaxy.getSystems()) {
                    if (s.getPlayersInSystem().contains(player)) {
                        s.getSystemShips().add(e);
                    }
                }
            }
        }
    }

    private List<String> getPlanetNamesFromFile() throws IOException {
        List<String> planetNames = new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        Path planetNamesFile = Paths.get("src/game/galaxy", "planet-names.txt");

        // try creating reader for file
        try (BufferedReader planetNamesReader = Files.newBufferedReader(planetNamesFile, charset)) {
            String lineFromFile;

            // loop through lines and add to list while EOF is not reached
            while ((lineFromFile = planetNamesReader.readLine()) != null) {
                planetNames.add(lineFromFile);
            }
        }
        return planetNames;
    }

    private List<String> getPlayerRacesFromFile() throws IOException {
        List<String> raceNames = new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        Path playerRacesFile = Paths.get("src/game/galaxy", "player-races.txt");

        // try creating reader for file
        try (BufferedReader playerRacesReader = Files.newBufferedReader(playerRacesFile, charset)) {
            String lineFromFile;

            // loop through lines and add to list while EOF is not reached
            while ((lineFromFile = playerRacesReader.readLine()) != null) {
                raceNames.add(lineFromFile);
            }
        }
        return raceNames;
    }

    private List<String> getPlayerNamesFromFile() throws IOException {
        List<String> playerNames = new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        Path playerNamesFile = Paths.get("src/game/galaxy", "player-names.txt");

        // try creating reader for file
        try (BufferedReader playerNamesReader = Files.newBufferedReader(playerNamesFile, charset)) {
            String lineFromFile;

            // loop through lines and add to list while EOF is not reached
            while ((lineFromFile = playerNamesReader.readLine()) != null) {
                playerNames.add(lineFromFile);
            }
        }
        return playerNames;
    }
}
