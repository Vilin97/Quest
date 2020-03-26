import static java.lang.Integer.max;


public abstract class Unit {
    private String name;
    private int level;
    private int hp;
    private int currentHP;
    private static int HPLevelMultiplier = 1000;

    public Unit(String name, int level, int hp, int currentHP) {
        this.name = name;
        this.level = level;
        this.hp = hp;
        this.currentHP = currentHP;
    }

    public Unit(String name, int level) {
        this.name = name;
        this.level = level;
        this.hp = HPLevelMultiplier*level;
        this.currentHP = HPLevelMultiplier*level;
    }

    public abstract String toString();

    public void levelUp() {
        setLevel(level + 1);
    }

    public abstract double getDamage();
    public abstract double getDefense();
    public abstract double getDodgeChance();

    public void attack(Unit o) {
        double r = Math.random();
        if (r < o.getDodgeChance()/100) {
            System.out.println(o.getName() + " dodged the attack by "+getName()+".");
        } else {
            int healthLost = max((int) (getDamage() - o.getDefense()),0);
            o.takeDamage(healthLost);
            System.out.println(getName()+" dealt "+healthLost+" damage to "+o.getName());
            System.out.println(o.getName()+"'s health is now "+o.getCurrentHP());
        }
    }

    public void takeDamage(int healthLost) {
        setCurrentHP(General.intWithinBounds(currentHP - healthLost, 0, getHP()));
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level >= 0){
            this.level = level;
        }
        else {
            throw new IllegalArgumentException("Level cannot be negative.");
        }
    }

    public int getHP() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp >= 0){
            this.hp = hp;
        }
        else {
            throw new IllegalArgumentException("HP cannot be negative.");
        }
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public static int getHPLevelMultiplier() {
        return HPLevelMultiplier;
    }

    public static void setHPLevelMultiplier(int HPLevelMultiplier) {
        Unit.HPLevelMultiplier = HPLevelMultiplier;
    }

    public boolean isAlive() {
        return currentHP > 0;
    }
}
