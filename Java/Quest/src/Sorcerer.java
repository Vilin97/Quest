public class Sorcerer extends Hero {

    public Sorcerer(String name, int MP, Stats stats, int level, int experience, Backpack backpack, Weapon weapon, Armor armor, SkillSet skills) {
        super(name, MP, stats, level, experience, backpack, weapon, armor, "Sorcerer",skills);
    }

    public Sorcerer(String name, int MP, Stats stats, int money, int experience) {
        super(name, MP, stats, money, experience, "Sorcerer");
    }

    public void levelUp() {
        super.levelUp();
        getStats().changeAgilityBy((int) (getStats().getAgility()* getHighLevelUpMultiplier()));
        getStats().changeDexterityBy((int) (getStats().getDexterity()* getHighLevelUpMultiplier()));
        getStats().changeStrengthBy((int) (getStats().getStrength()* getLowLevelUpMultiplier()));
    }
}
