import java.util.Objects;

public class Player {

    private String name;
    private String race;
    private String color;

    public Player(String name, String race, String color) {
        this.name = name;
        this.race = race;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(race, player.race) && Objects.equals(color, player.color);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, race, color);
    }

    @Override
    public String toString() {
        return "Player{" + "name='" + name + '\'' + ", race='" + race + '\'' + ", color='" + color + '\'' + '}';
    }
}
