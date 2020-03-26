public class Monster extends Unit {
    //Name/level/damage/defense/dodge chance
    private int damage;
    private int defense;
    private int dodgeChance;
    private String className;

    public Monster(String name, int level, int hp, int currentHP, int damage, int defense, int dodgeChance, String className) {
        super(name, level, hp, currentHP);
        this.damage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
        this.className = className;
    }

    public Monster(String name, int level, int damage, int defense, int dodgeChance, String className) {
        super(name, level);
        this.damage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
        this.className = className;
    }

    public String toString() {
        return String.format("%s | %s | %d | %d | %d | %d", className, getName(), getLevel(), damage, defense, dodgeChance);
    }

    public String detailedToString() {
        // detailed information about the monster
        String s = "~~~~~~\n";
        s += String.format("%s %s. Level: %d, HP: %d.\n", className, getName(), getLevel(), getCurrentHP());
        s += "Damage: "+damage+". "+"Defense: "+defense+". "+"Dodge Chance: "+dodgeChance+".";
        s += "\n~~~~~~";
        return s;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public double getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public double getDodgeChance() {
        return dodgeChance;
    }

    public void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }


}
