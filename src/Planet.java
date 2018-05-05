import units.Unit;

import java.util.ArrayList;
import java.util.List;

public class Planet {

    private String name;
    private int resourceProduction;
    Player playerInControl;

    public Planet(String name, int resourceProduction) {
        this.name = name;
        this.resourceProduction = resourceProduction;
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
