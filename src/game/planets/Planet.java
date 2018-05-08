/*
 * Made by:
 * Jonas Krogh Hansen, Software
 * jh17@student.aau.dk
 */

package game.planets;

import game.player.Player;

import java.util.Random;

public class Planet {
    private Random rand = new Random();
    private String name;
    private int resourceProduction;
    private Player playerInControl;

    public Planet(String name) {
        this.name = name;

        // assign random resourceProduction upon creation
        this.resourceProduction = rand.nextInt(7);
        this.playerInControl = null;
    }

    public String getName() {
        return name;
    }

    public int getResourceProduction() {
        return resourceProduction;
    }

    @Override
    public String toString() {
        return "Planet{" + "name='" + name + '\'' + ",\n resourceProduction=" + resourceProduction + ",\n playerInControl=" + playerInControl + "}\n";
    }

    public void setPlayerInControl(Player playerInControl) {
        this.playerInControl = playerInControl;
    }
}
