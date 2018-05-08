/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.galaxy;

import game.planets.Planet;
import game.player.Player;
import game.systems.HexaSystem;
import game.units.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Galaxy extends GalaxyGenerator {
    public List<HexaSystem> systems;
    private List<Player> players;

    public Galaxy() {
        this.systems = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    public List<HexaSystem> getSystems() {
        return systems;
    }

    @Override
    public String toString() {
        return "Galaxy{" + "systems=" + systems + ", players=" + players + "}\n";
    }

    public List<Unit> getShips() {
        List<Unit> shipsInGalaxy = new ArrayList<>();
        List<Unit> ships;

        // for each system in galaxy
        for(HexaSystem e : systems) {

            // get ships in system and add to list shipsInGalaxy
            ships = e.getSystemShips();
            shipsInGalaxy.addAll(ships);
        }
        return shipsInGalaxy;
    }

    public List<Planet> getPlanets() {
        List<Planet> planetsInGalaxy = new ArrayList<>();
        List<Planet> planets;

        // for each system in galaxy
        for(HexaSystem e : systems) {

            // get planets in system and add to list planetsInGalaxy
            planets = e.getSystemPlanets();
            planetsInGalaxy.addAll(planets);
        }
        return planetsInGalaxy;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Galaxy sampleGalaxy() {
        // create galaxy
        Galaxy galaxy = new Galaxy();

        // create players
        Player blue = new Player("Crassus", "The Emirates of Hacan", galaxy);
        Player red = new Player("Pompey", "The Federation of Sol", galaxy);

        // add players to galaxy
        galaxy.getPlayers().add(blue);
        galaxy.getPlayers().add(red);

        // create systems
        galaxy.getSystems().add(new HexaSystem(galaxy));
        galaxy.getSystems().add(new HexaSystem(galaxy));
        galaxy.getSystems().add(new HexaSystem(galaxy));
        galaxy.getSystems().add(new HexaSystem(galaxy));
        galaxy.getSystems().add(new HexaSystem(galaxy));
        galaxy.getSystems().add(new HexaSystem(galaxy));
        galaxy.getSystems().add(new HexaSystem(galaxy));

        // add planets to systems
        galaxy.getSystems().get(0).addPlanet(new Planet("Mecatol Rex"));
        galaxy.getSystems().get(1).addPlanet(new Planet("Vega Minor"));
        galaxy.getSystems().get(1).addPlanet(new Planet("Vega Major"));
        galaxy.getSystems().get(3).addPlanet(new Planet("Industrex"));
        galaxy.getSystems().get(4).addPlanet(new Planet("Rigel I"));
        galaxy.getSystems().get(4).addPlanet(new Planet("Rigel II"));
        galaxy.getSystems().get(6).addPlanet(new Planet("Mirage"));

        blue.getShips().add(new DreadnoughtUnit(blue));
        blue.getShips().add(new DreadnoughtUnit(blue));
        blue.getShips().add(new DestroyerUnit(blue));

        red.getShips().add(new CruiserUnit(red));
        red.getShips().add(new CruiserUnit(red));
        red.getShips().add(new CarrierUnit(red));

        // add blue ships to designated system
        galaxy.getSystems().get(0).getSystemShips().addAll(blue.getShips());

        // add red ships to designated system
        galaxy.getSystems().get(1).getSystemShips().addAll(red.getShips());

        return galaxy;
    }

    // problem 11
    public void createPlanetaryControlFile() throws IOException {
        Path planetaryControlFile = Paths.get( "src/planetary-control.txt");

        // if file already exists, delete it and create new
        Files.deleteIfExists(planetaryControlFile);
        Files.createFile(planetaryControlFile);
        PrintWriter writer = new PrintWriter("src/planetary-control.txt", "UTF-8");

        // for every player in the galaxy, write headliner
        for(Player e : this.getPlayers()) {
            writer.println(String.format("%-15s %s (%s)",
                    e.getColor() + " Player:", e.getName(), e.getRace()));


            // for every planet the player controls, write planet name
            for(Planet p : e.getControlledPlanets()) {
                writer.println(String.format("%15s %s", "", p.getName()));
            }

            // add newline once player no longer controls any planets, for formatting purposes
            writer.print("\n");
        }
        writer.close();
    }

    public void checkIfCombatIsNecessaryInGalaxy() {

        // indexes of the two players in system
        int indexOfPlayerOne = 0;
        int indexOfPlayerTwo = 1;

        // for each system in galaxy
        for(HexaSystem e : systems) {

            // if there is more than one player in system
            if(e.getPlayersInSystem().size() > 1) {

                // find winner from combat
                Player winner = e.concludeCombat(e.getPlayersInSystem().get(indexOfPlayerOne),
                        e.getPlayersInSystem().get(indexOfPlayerTwo));
                System.out.println("A battle was fought in the " + e.getCardinal() + " system, " +
                        "and " + winner.getName() + "(" + winner.getColor() + ", " +
                        winner.getRace() + ") is the winner!");

                // add all planets to list of planets controlled by player
                winner.getControlledPlanets().addAll(e.getSystemPlanets());

                // for each planet in system, set winner as player in control
                for(Planet p : e.getSystemPlanets()) {
                    p.setPlayerInControl(winner);
                }
            }
        }
    }
}


