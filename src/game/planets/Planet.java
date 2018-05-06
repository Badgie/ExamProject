package game.planets;

import game.player.Player;

import java.util.Random;

public class Planet {
    Random rand = new Random();
    private String name;
    private int resourceProduction;
    private Player playerInControl;

    public Planet(String name) {
        this.name = name;
        this.resourceProduction = rand.nextInt(7);
        this.playerInControl = null;
    }

    public String getName() {
        return name;
    }

    public int getResourceProduction() {
        return resourceProduction;
    }

    public Player getPlayerInControl() {
        return playerInControl;
    }

    @Override
    public String toString() {
        return "Planet{" + "name='" + name + '\'' + ", resourceProduction=" + resourceProduction + ", playerInControl=" + playerInControl + '}';
    }

    public void setPlayerInControl(Player playerInControl) {
        this.playerInControl = playerInControl;
    }
}
