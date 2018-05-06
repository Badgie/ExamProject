import exceptions.PrintException;
import units.*;

import java.io.*;
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
    public Galaxy generateGalaxy() {
        Galaxy galaxy = new Galaxy();
        try {
            galaxy.getSystems().addAll(generateSystems());

            try {
                generatePlanets(galaxy);
            } catch (IOException e) {
                e.getMessage();
            }

            generatePlayers(galaxy);

            checkIfGalaxyIsLegal(galaxy);

        } catch(PrintException e) {
            e.getMessage();
        }

        return galaxy;
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
    private List<HexaSystem> generateSystems() {
        List<HexaSystem> systems = new ArrayList<>();
        String[] cardinals = {"Center", "North", "North East", "North West", "South",
                "South East", "South West"};

        // for each cardinal in cardinals[], add new system to list
        for (String e : cardinals) {
            systems.add(new HexaSystem(e));
        }

        return systems;
    }
    // only run in generateGalaxy
    private void generatePlanets(Galaxy galaxy) throws IOException {
        Random rand = new Random();

        for(HexaSystem e : galaxy.getSystems()) {
            if(e.getCardinal().equals("Center")) {
                e.addPlanet(new Planet("Mecatol Rex"));
            } else {
                for(int i = 0; i < rand.nextInt(3); i++) {
                    e.addPlanet(new Planet(getPlanetNamesFromFile().get(rand.nextInt(57))));
                }
            }
        }
    }

    // only run in generateGalaxy
    private void generatePlayers(Galaxy galaxy) {
        String[] colors = {"Green", "Red", "Yellow", "Purple", "Black", "Blue"};
        Random rand = new Random();
        for(int i = 0; i < rand.nextInt(5) + 2; i++) {
            galaxy.getPlayers().add(new Player(getPlayerNamesFromFile().get(rand.nextInt(29)),
                    getPlayerRacesFromFile().get(rand.nextInt(17)),
                    colors[rand.nextInt(6)]));
        }
        for(Player e : galaxy.getPlayers()) {
            generateShips(e);
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

    private List<String> getPlanetNamesFromFile() {
        List<String> planetNames = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File("data/planet-names.txt"));
            while(scan.hasNextLine()) {
                planetNames.add(scan.nextLine());
            }
            scan.close();
        } catch(FileNotFoundException e) {
            e.getMessage();
        }

        return planetNames;
    }

    private List<String> getPlayerRacesFromFile() {
        List<String> raceNames = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File("data/player-races.txt"));
            while(scan.hasNextLine()) {
                raceNames.add(scan.nextLine());
            }
            scan.close();
        } catch(FileNotFoundException e) {
            e.getMessage();
        }
        return raceNames;
    }

    private List<String> getPlayerNamesFromFile() {
        List<String> playerNames = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File("data/player-names.txt"));
            while(scan.hasNextLine()) {
                playerNames.add(scan.nextLine());
            }
            scan.close();
        } catch(FileNotFoundException e) {
            e.getMessage();
        }
        return playerNames;
    }
}


