package game.galaxy;

import game.exceptions.PrintException;
import game.planets.Planet;
import game.player.Player;
import game.systems.HexaSystem;
import game.units.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Galaxy {
    public List<HexaSystem> systems;
    public List<Player> players;

    public Galaxy() {
        this.systems = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public List<HexaSystem> getSystems() {
        return systems;
    }

    @Override
    public String toString() {
        return "Galaxy{" + "systems=" + systems + ", players=" + players + '}';
    }

    public List<Unit> getShips() {
        List<Unit> resolved = new ArrayList<>();
        List<Unit> ships;
        for(HexaSystem e : systems) {
            ships = e.getSystemShips();
            resolved.addAll(ships);
        }
        return resolved;
    }

    public List<Planet> getPlanets() {
        List<Planet> resolved = new ArrayList<>();
        List<Planet> planets;
        for(HexaSystem e : systems) {
            planets = e.getSystemPlanets();
            resolved.addAll(planets);
        }
        return resolved;
    }

    public List<Player> getPlayers() {
        return players;
    }

    // problem 12
    public Galaxy constructGalaxy() {
        try {
            generateSystems(this);
            generatePlanets(this);
            generatePlayers(this);
            checkIfGalaxyIsLegal(this);

        } catch(PrintException e) {
            e.getMessage();
        }

        return this;
    }

    public void sampleGalaxy() {
        // galaxy
        Galaxy galaxy = new Galaxy();

        // players
        Player blue = new Player("Crassus", "The Emirates of Hacan", "Blue");
        Player red = new Player("Pompey", "The Federation of Sol", "Red");

        // systems
        List<HexaSystem> sampleSystems = new ArrayList<>();
        sampleSystems.add(new HexaSystem("Center"));
        sampleSystems.add(new HexaSystem("North"));
        sampleSystems.add(new HexaSystem("North East"));
        sampleSystems.add(new HexaSystem("South East"));
        sampleSystems.add(new HexaSystem("South"));
        sampleSystems.add(new HexaSystem("South West"));
        sampleSystems.add(new HexaSystem("North West"));
        galaxy.systems.addAll(sampleSystems);

        // add planets
        galaxy.getSystems().get(0).addPlanet(new Planet("Mecatol Rex"));
        galaxy.getSystems().get(1).addPlanet(new Planet("Vega Minor"));
        galaxy.getSystems().get(1).addPlanet(new Planet("Vega Major"));
        galaxy.getSystems().get(3).addPlanet(new Planet("Industrex"));
        galaxy.getSystems().get(4).addPlanet(new Planet("Rigel I"));
        galaxy.getSystems().get(4).addPlanet(new Planet("Rigel II"));
        galaxy.getSystems().get(6).addPlanet(new Planet("Mirage"));

        // add blue ships
        galaxy.getSystems().get(0).addShip(new DreadnoughtUnit(blue));
        galaxy.getSystems().get(0).addShip(new DreadnoughtUnit(blue));
        galaxy.getSystems().get(0).addShip(new DestroyerUnit(blue));

        // add red ships
        galaxy.getSystems().get(1).addShip(new CruiserUnit(red));
        galaxy.getSystems().get(1).addShip(new CruiserUnit(red));
        galaxy.getSystems().get(1).addShip(new CarrierUnit(red));
    }

    // Problem 9
    // TODO: implement fourth criteria
    private boolean checkIfGalaxyIsLegal(Galaxy galaxy) throws PrintException {
        try {
            boolean condOne = checkCenterGalaxyPlanets(galaxy);
            boolean condTwo = checkForDuplicatePlanets(galaxy);
            boolean condThree = checkForMoreThanThreePlanets(galaxy);
            boolean condFour = checkCardinalDirections(galaxy);

            if (condOne && condTwo && condThree && condFour) {

            } else {
                throw new PrintException("ERROR: Galaxy is not legal.");
            }
        } catch(PrintException e) {
            e.getMessage();
        }
        return true;
    }

    // first criteria
    private boolean checkCenterGalaxyPlanets(Galaxy galaxy) throws PrintException {
            if(getCenterSystemPlanets(galaxy).contains("Mecatol Rex")
                    && getCenterSystemPlanets(galaxy).size() == 1) {
            } else {
                throw new PrintException("ERROR: Either the center system contains planets other than " +
                    "Mecatol Rex, or it does not contain Mecatol Rex.");
        }
        return true;
    }

    // second criteria
    private boolean checkForDuplicatePlanets(Galaxy galaxy) throws PrintException {
        if(galaxy.getPlanets().size() == getGalaxyPlanetNames(galaxy).size()) {
        } else {
            throw new PrintException("ERROR: A planet exists more than once in the galaxy.");
        }
        return true;
    }

    // third criteria
    private boolean checkForMoreThanThreePlanets(Galaxy galaxy) throws PrintException {
        for(HexaSystem e : galaxy.getSystems()) {
            if(getSystemPlanetNames(e).size() <= 3) {
            } else {
                throw new PrintException("ERROR: A system contains more than three planets.");
            }
        }
        return true;
    }

    // fourth criteria
    private boolean checkCardinalDirections(Galaxy galaxy) throws PrintException {
        for(HexaSystem e : galaxy.getSystems()) {
            e.setNeighbors(galaxy, e);
        }

        return true;
    }

    // helpermethods
    private ArrayList<String> getGalaxyPlanetNames(Galaxy galaxy) {
        ArrayList<String> array = new ArrayList<>();
        for(Planet e : galaxy.getPlanets()) {
            array.add(e.getName());
        }
        return array;
    }

    private ArrayList<String> getSystemPlanetNames(HexaSystem system) {
        ArrayList<String> array = new ArrayList<>();
        for(Planet e : system.getSystemPlanets()) {
            array.add(e.getName());
        }
        return array;
    }

    private ArrayList<String> getCenterSystemPlanets(Galaxy galaxy) {
        ArrayList<String> planets = new ArrayList<>();
        for(int i = 0; i < galaxy.getSystems().size(); i++) {
            if(galaxy.getSystems().get(i).getCardinal().equals("Center")) {
                for (Planet e : galaxy.getSystems().get(i).getSystemPlanets()) {
                    planets.add(e.toString());
                }
            }
        }
        return planets;
    }

    private void checkNeighborValidity(Galaxy galaxy, HexaSystem e) {
        List<HexaSystem> systems = galaxy.getSystems();
        int i = galaxy.getSystems().indexOf(e);
        if(systems.get(i).getCardinal().equals("Center")) {
            systems.get(i).getNeighbors().size();
        }
    }

    // helpermethods
    // only run in generateGalaxy
    private void generateSystems(Galaxy galaxy) {
        String[] cardinals = {"Center", "North", "North East", "North West", "South",
                "South East", "South West"};

        // for each cardinal in cardinals[], add new system to list
        for (String e : cardinals) {
            galaxy.getSystems().add(new HexaSystem(e));
        }
    }
    // only run in generateGalaxy
    private void generatePlanets(Galaxy galaxy) {
        Random rand = new Random();

        try {
            List<String> planetNames = new ArrayList<>(getPlanetNamesFromFile());
            for (HexaSystem e : galaxy.getSystems()) {
                if (e.getCardinal().equals("Center")) {
                    e.addPlanet(new Planet("Mecatol Rex"));
                } else {
                    for (int i = 0; i < rand.nextInt(3); i++) {
                        e.addPlanet(new Planet(planetNames.get(rand.nextInt(57))));
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // only run in generateGalaxy
    private void generatePlayers(Galaxy galaxy) {
        String[] colors = {"Green", "Red", "Yellow", "Purple", "Black", "Blue"};
        Random rand = new Random();

        try {
            List<String> playerNames = new ArrayList<>(getPlayerNamesFromFile());
            List<String> playerRaces = new ArrayList<>(getPlayerRacesFromFile());

            for(int i = 0; i < rand.nextInt(5) + 2; i++) {
                galaxy.getPlayers().add(new Player(playerNames.get(rand.nextInt(playerNames.size())),
                        playerRaces.get(rand.nextInt(playerRaces.size())),
                        colors[rand.nextInt(colors.length)]));
            }
            for(Player e : galaxy.getPlayers()) {
                generateShips(e);
                addPlayerShipsToRandomSystems(e, galaxy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // only run in generatePlayers
    private void generateShips(Player player) {
        int maxShips = 7;
        Random rand = new Random();

        for(int i = 0; i < maxShips; i++) {
            int randomShip = rand.nextInt(4);

            switch(randomShip) {
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

    private void addPlayerShipsToRandomSystems(Player player, Galaxy galaxy) {
        Random rand = new Random();
        int numberOfSystems = 7;
        for(Unit e : player.getShips()) {
            int randomNumber = rand.nextInt(numberOfSystems);
            if(galaxy.getSystems().get(randomNumber).getPlayersInSystem().size() < 2) {
                galaxy.getSystems().get(randomNumber).addShip(e);
            }
        }
    }

    private List<String> getPlanetNamesFromFile() throws IOException {
        List<String> planetNames = new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        Path planetNamesFile = Paths.get("src/game/galaxy", "planet-names.txt");

        try(BufferedReader planetNamesReader = Files.newBufferedReader(planetNamesFile, charset)) {
            String lineFromFile;
            while((lineFromFile = planetNamesReader.readLine()) != null) {
                planetNames.add(lineFromFile);
            }
        }
        return planetNames;
    }

    private List<String> getPlayerRacesFromFile() throws IOException {
        List<String> raceNames = new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        Path playerRacesFile = Paths.get("src/game/galaxy", "player-races.txt");

        try(BufferedReader playerRacesReader = Files.newBufferedReader(playerRacesFile, charset)) {
            String lineFromFile;
            while((lineFromFile = playerRacesReader.readLine()) != null) {
                raceNames.add(lineFromFile);
            }
        }
        return raceNames;
    }

    private List<String> getPlayerNamesFromFile() throws IOException {
        List<String> playerNames = new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        Path playerNamesFile = Paths.get("src/game/galaxy", "player-names.txt");

        try(BufferedReader playerNamesReader = Files.newBufferedReader(playerNamesFile, charset)) {
            String lineFromFile;
            while((lineFromFile = playerNamesReader.readLine()) != null) {
                playerNames.add(lineFromFile);
            }
        }
        return playerNames;
    }

    public void checkIfCombatIsNecessaryInSystems() {
        int indexOfPlayerOne = 0;
        int indexOfPlayerTwo = 1;

        for(HexaSystem e : systems) {
            if(e.getPlayersInSystem().size() > 1) {
                e.concludeCombat(e.getPlayersInSystem().get(indexOfPlayerOne),
                        e.getPlayersInSystem().get(indexOfPlayerTwo));
            }
        }
    }
}


