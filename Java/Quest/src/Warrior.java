public class Warrior extends Hero{

    public Warrior(String name, int MP, Stats stats, int level, int experience, Backpack backpack, Weapon weapon, Armor armor, SkillSet skills) {
        super(name, MP, stats, level, experience, backpack, weapon, armor, "Warrior",skills);
    }

    public Warrior(String name, int MP, Stats stats, int money, int experience) {
        super(name, MP, stats, money, experience, "Warrior");
    }

    public void levelUp() {
        super.levelUp();
        getStats().changeAgilityBy((int) (getStats().getAgility()* getHighLevelUpMultiplier()));
        getStats().changeDexterityBy((int) (getStats().getDexterity()* getHighLevelUpMultiplier()));
        getStats().changeStrengthBy((int) (getStats().getStrength()* getLowLevelUpMultiplier()));
    }
}
