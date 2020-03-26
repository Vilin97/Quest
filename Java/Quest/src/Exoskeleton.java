public class Exoskeleton extends Monster {
    public Exoskeleton(String name, int level, int hp, int currentHP, int damage, int defense, int dodgeChance) {
        super(name, level, hp, currentHP, damage, defense, dodgeChance, "Exoskeleton");
    }

    public Exoskeleton(String name, int level, int damage, int defense, int dodgeChance) {
        super(name, level, damage, defense, dodgeChance, "Exoskeleton");
    }
}
