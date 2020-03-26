public class Spirit extends Monster {
    public Spirit(String name, int level, int hp, int currentHP, int damage, int defense, int dodgeChance) {
        super(name, level, hp, currentHP, damage, defense, dodgeChance, "Spirit");
    }

    public Spirit(String name, int level, int damage, int defense, int dodgeChance) {
        super(name, level, damage, defense, dodgeChance, "Spirit");
    }
}
