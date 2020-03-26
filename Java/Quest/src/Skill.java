public abstract class Skill {
    // class for a skill
    // Name/cost/required level/damage/mana cost
    private String name;
    private int cost;
    private int level;
    private int damage;
    private int manaCost;
    private String className;

    public Skill(String name, int cost, int level, int damage, int manaCost, String className) {
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.damage = damage;
        this.manaCost = manaCost;
        this.className = className;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %d | %d | %d | %d", className, name, cost, manaCost, damage, level);
    }

    public abstract void doSideEffect(Monster monster);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
