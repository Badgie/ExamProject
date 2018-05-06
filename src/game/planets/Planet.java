import units.Unit;

import java.util.ArrayList;
import java.util.List;
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

    public void setPlayerInControl(Player playerInControl) {
        this.playerInControl = playerInControl;
    }
}
