public class Potion extends Item {
    private int attIncrease;
    private static String thisType = "Potion";

    public Potion(String name, int cost, int attIncrease, int level) {
        super(name, cost, level, thisType);
        this.attIncrease = attIncrease;
    }

    @Override
    public String toString() {
        return String.format("Potion | %s | %d | %d | %d", getName(), getCost(), attIncrease, getLevel());
    }

    public int getAttIncrease() {
        return attIncrease;
    }

    public void setAttIncrease(int attIncrease) {
        this.attIncrease = attIncrease;
    }
}
