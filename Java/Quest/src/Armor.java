public class Armor extends Item {
    private int damageReduction;
    private static String emptyName = "Unarmored";
    private static int emptyCost = 0;
    private static int emptyDamageReduction = 0;
    private static int emptyLevel = 0;
    private static String thisType = "Armor";

    public Armor() {
        // empty armor
        this(emptyName,emptyCost,emptyDamageReduction,emptyLevel);
    }

    public Armor(String name, int cost, int damageReduction, int level) {
        super(name, cost, level, thisType);
        this.damageReduction = damageReduction;
    }

    public boolean isEmpty() {
        // true iff the weapon is the empty armor
        return getName().equals(emptyName);
    }

    @Override
    public String toString() {
        return String.format("Armor | %s | %d | %d | %d", getName(), getCost(), damageReduction, getLevel());
    }

    public String detailedToString() {
        // detailed information about the armor
        String s = "";
        s += String.format("%s (armor). Cost: %d, required level: %d, damage reduction: %d."
                , getName(), getCost(), getLevel(), damageReduction);
        return s;
    }

    public int getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(int damageReduction) {
        this.damageReduction = damageReduction;
    }
}
