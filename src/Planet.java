public class Planet {

    private String name;
    private int resourceProduction;

    public Planet(String name, int resourceProduction) {
        this.name = name;
        this.resourceProduction = resourceProduction;
    }

    public String getName() {
        return name;
    }

    public int getResourceProduction() {
        return resourceProduction;
    }
}
