import exceptions.PrintException;
import units.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Galaxy {
    public List<HexaSystem> systems;

    public Galaxy() {
        this.systems = new ArrayList<>();
    }

    public List<HexaSystem> getSystems() {
        return systems;
    }

    public List<Unit> getShips() {
        List<Unit> resolved = new ArrayList<>();
        List<Unit> ships;
        for(HexaSystem e : systems) {
            ships = e.getSystemShips();
            for (Unit u : ships) {
                resolved.add(u);
            }
        }
        return resolved;
    }

    public List<Planet> getPlanets() {
        List<Planet> resolved = new ArrayList();
        List<Planet> planets;
        for(HexaSystem e : systems) {
            planets = e.getSystemPlanets();
            for (Planet u : planets) {
                resolved.add(u);
            }
        }
        return resolved;
    }

    public void sampleGalaxy() {
        // Generate galaxy
        Galaxy galaxy = new Galaxy();

        // Players
        Player blue = new Player("Crassus", "The Emirates of Hacan", "Blue");
        Player red = new Player("Pompey", "The Federation of Sol", "Red");

        // Systems
        List<HexaSystem> sampleSystems = new ArrayList<>();
        sampleSystems.add(new HexaSystem("Center"));
        sampleSystems.add(new HexaSystem("North"));
        sampleSystems.add(new HexaSystem("North East"));
        sampleSystems.add(new HexaSystem("South East"));
        sampleSystems.add(new HexaSystem("South"));
        sampleSystems.add(new HexaSystem("South West"));
        sampleSystems.add(new HexaSystem("North West"));
        galaxy.systems.addAll(sampleSystems);

        // Add planets
        galaxy.getSystems().get(0).addPlanet(new Planet("Mecatol Rex", 5));
        galaxy.getSystems().get(1).addPlanet(new Planet("Vega Minor", 1));
        galaxy.getSystems().get(1).addPlanet(new Planet("Vega Major", 3));
        galaxy.getSystems().get(3).addPlanet(new Planet("Industrex", 6));
        galaxy.getSystems().get(4).addPlanet(new Planet("Rigel I", 3));
        galaxy.getSystems().get(4).addPlanet(new Planet("Rigel II", 2));
        galaxy.getSystems().get(6).addPlanet(new Planet("Mirage", 4));

        // Add blue ships to system
        galaxy.getSystems().get(0).addShip(new DreadnoughtUnit(blue));
        galaxy.getSystems().get(0).addShip(new DreadnoughtUnit(blue));
        galaxy.getSystems().get(0).addShip(new DestroyerUnit(blue));

        // Add red ships to system
        galaxy.getSystems().get(1).addShip(new CruiserUnit(red));
        galaxy.getSystems().get(1).addShip(new CruiserUnit(red));
        galaxy.getSystems().get(1).addShip(new CarrierUnit(red));
    }

    public boolean checkIfGalaxyIsLegal(Galaxy galaxy) {
        boolean condOne = checkCenterGalaxyPlanets(galaxy);
        boolean condTwo = checkForDuplicatePlanets(galaxy);
        boolean condThree = checkForMoreThanThreePlanets(galaxy);
        boolean condFour = checkCardinalDirections(galaxy);
        boolean verdict = false;

        if(condOne && condTwo && condThree && condFour) {
            verdict = true;
        }

        return verdict;
    }

    //TODO: fix this monstrosity
    private boolean checkCenterGalaxyPlanets(Galaxy galaxy) throws PrintException {
        boolean verdict = false;
        try {
            int center = 0;
            int systemIndex = galaxy.getSystems().size();
            for(int i = 0; i < systemIndex; i++) {
                if(galaxy.getSystems().get(i).getCardinal().equals("Center")) {
                    center = i;
                }
            }
            if(galaxy.getSystems().get(center).getSystemPlanets().get(0).getName().equals("Mecatol Rex")
                    && galaxy.getSystems().get(center).getSystemPlanets().size() == 1) {
                verdict = true;
            }
        } catch(PrintException e) {
            String message = "ERROR: Either Mecatol Rex does not exist, or there are too many planets in the center system.";
            e.printMessage(message);
        }
        return verdict;
    }

    private boolean checkForDuplicatePlanets(Galaxy galaxy) throws PrintException {
        try {
            HashSet<String> planets = new HashSet<>();
            for(HexaSystem e : galaxy.getSystems()) {
                planets.addAll(getSystemPlanetNames(e));
            }
        } catch(PrintException e) {
            String message = "";
            e.printMessage(message);
        }
        return true;
    }

    private boolean checkForMoreThanThreePlanets(Galaxy galaxy) throws PrintException {
        boolean verdict = false;
        try {
            for(HexaSystem e : galaxy.getSystems()) {
                if(getSystemPlanetNames(e).size() <= 3) {
                    verdict = true;
                }
            }
        } catch(PrintException e) {

        }
        return verdict;
    }

    private boolean checkCardinalDirections(Galaxy galaxy) throws PrintException {
        boolean verdict = false;
        try {

        } catch(PrintException e) {

        }

        return verdict;
    }

    private ArrayList<String> getSystemPlanetNames(HexaSystem system) {
        ArrayList<String> array = new ArrayList<>();
        for(Planet e : system.getSystemPlanets()) {
            array.add(e.getName());
        }
        return array;
    }
}


