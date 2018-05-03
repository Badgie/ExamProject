import units.Unit;

import java.util.ArrayList;
import java.util.List;

public class Galaxy {
    private List<HexaSystem> systems;

    public Galaxy() {
        this.systems = new ArrayList<>();
    }

    public List<HexaSystem> getSystems() {
        return systems;
    }

    public List<Unit> getShips() {
        return ;
    }
}
