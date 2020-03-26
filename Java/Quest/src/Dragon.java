public class Dragon extends Monster {

    public Dragon(String name, int level, int hp, int currentHP, int damage, int defense, int dodgeChance) {
        super(name, level, hp, currentHP, damage, defense, dodgeChance, "Dragon");
    }

    public Dragon(String name, int level, int damage, int defense, int dodgeChance) {
        super(name, level, damage, defense, dodgeChance, "Dragon");
    }
}
