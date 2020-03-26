public class Weapon extends Item {
    private int damage;
    private int hands;
    private static String emptyName = "Fists";
    private static int emptyCost = 0;
    private static int emptyLevel = 0;
    private static int emptyDamage = 0;
    private static int emptyHands = 2;
    private static String thisType = "Weapon";

    public Weapon() {
        // empty weapon
        this(emptyName, emptyCost,emptyLevel,emptyDamage,emptyHands);
    }

    public Weapon(String name, int cost, int level, int damage, int hands) {
        super(name, cost, level, thisType);
        this.damage = damage;
        this.hands = hands;
    }

    public boolean isEmpty() {
        // true iff the weapon is the empty weapon
        return getName().equals(emptyName);
    }

    @Override
    public String toString() {
        return String.format("Weapon | %s | %d | %d | %d | %d", getName(), getCost(), hands, damage, getLevel());
    }

    public String detailedToString() {
        // detailed information about the weapon
        String s = "";
        s += String.format("%s (weapon). Cost: %d, required level: %d, damage: %d, required hands: %d."
                , getName(), getCost(), getLevel(), damage, hands);
        return s;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
