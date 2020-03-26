import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Market extends Cell {
    private static String marketSymbol = "M";
    private Weapon[] weapons;
    private Armor[] armors;
    private Potion[] potions;
    private Item[] items;
    private Skill[] skills;
    private String legend = "Legend: b, i = buy item i; s, i = sell item i. Don't forget the comma!";
    private String welcomeMessage = "Welcome to the Market! Here you can buy and sell items.";
    private static String path = "C:/Users/Vasily/GitHub/Java/Quest/data/";

    public Market() {
        super(marketSymbol);
        setWeapons(ReadFile.readWeaponData(path+"Weaponry.txt"));
        setArmors(ReadFile.readArmorData(path+"Armory.txt"));
        setPotions(ReadFile.readPotionData(path+"Potions.txt"));
        Item[] items = new Item[weapons.length+armors.length+potions.length];
        System.arraycopy(weapons, 0, items, 0, weapons.length);
        System.arraycopy(armors, 0, items, weapons.length, armors.length);
        for (int i = 0; i < potions.length; i++) { items[i + weapons.length + armors.length] = potions[i]; }
        this.items = items;

        Skill[] fireSpells = ReadFile.readSkillData(path+"FireSpells.txt","F");
        Skill[] iceSpells = ReadFile.readSkillData(path+"IceSpells.txt","I");
        Skill[] lightningSpells = ReadFile.readSkillData(path+"LightningSpells.txt","L");
        Object[] flat = General.flatten(new Skill[][]{fireSpells,iceSpells,lightningSpells}).toArray();
        skills = new Skill[flat.length];
        for (int i = 0; i < flat.length; i++) {
            skills[i] = (Skill) flat[i];
        }
    }



    public boolean isMarket() { return true; }
    public boolean isEmpty() { return false; }
    public boolean isInaccessible() { return false; }

    public void buyItem(int itemID, Hero hero) {
        // hero buys item under ID itemID
        Item item = items[itemID];
        if (item.getCost() > hero.getBackpack().getMoney()) { System.out.println("Not enough money"); }
        else if (item.getLevel() > hero.getLevel()) { System.out.println("Level not high enough"); }
        else {
            hero.getBackpack().addItem(items[itemID]);
            hero.getBackpack().changeMoneyBy(-item.getCost());
        }
    }

    public void buySpell(int id, Hero hero) {
        // hero buys spell under ID itemID
        Skill skill = skills[id];
        if (skill.getCost() > hero.getBackpack().getMoney()) { System.out.println("Not enough money"); }
        else if (skill.getLevel() > hero.getLevel()) { System.out.println("Level not high enough"); }
        else {
            hero.getSkillSet().addSkill(skill);
            hero.getBackpack().changeMoneyBy(-skill.getCost());
        }
    }

    public void sell(int itemID, Hero hero) {
        // hero sells item under ID itemID (in hero's backpack) for twice less than the cost
        if (itemID < hero.getBackpack().getItems().length){
            Backpack backpack = hero.getBackpack();
            backpack.changeMoneyBy(backpack.getItems()[itemID].getCost()/2);
            backpack.removeItem(itemID);
        }
    }

    public Weapon[] getWeapons() {
        return weapons;
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }

    public void setWeapons(Weapon[] weapons) {
        this.weapons = weapons;
    }

    public Armor[] getArmors() {
        return armors;
    }

    public void setArmors(Armor[] armors) {
        this.armors = armors;
    }

    public Potion[] getPotions() {
        return potions;
    }

    public void setPotions(Potion[] potions) {
        this.potions = potions;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public void displayWeapons() {
        String s = "Weapons:\n" +
                "Type | name | cost | required hands | damage | required level\n";
        System.out.println(s+ General.myArrayToString(weapons));
    }

    public void displayArmors() {
        String s = "Armor:\n" +
                "Type | name | cost | damage reduction | required level\n";
        System.out.println(s+ General.myArrayToString(armors));
    }

    public void displayPotions() {
        String s = "Potions:\n" +
                "Type | name | cost | attribute increase | required level\n";
        System.out.println(s+ General.myArrayToString(potions));
    }

    private int[] getIds(String type) {
        int num = getCount(type);
        int[] ids = new int[num];
        int j = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getType().equals(type)) {
                ids[j] = i;
                j ++;
            }
        }
        return ids;
    }

    private int getCount(String type) {
        int res = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getType().equals(type)) { res++; }
        }
        return res;
    }

    private String weaponsToString() {
        Weapon[] weapons = getWeapons();
        String s = "Weapons:\n" +
                "ID. Type | name | cost | required hands | damage | required level\n";
        s += General.arrayToStringWithIds(weapons, General.arrayPlusOne(getIds("Weapon")));
        return s;
    }

    private String armorsToString() {
        Armor[] armors = getArmors();
        String s = "Armor:\n" +
                "ID. Type | name | cost | damage reduction | required level\n";
        s += General.arrayToStringWithIds(armors, General.arrayPlusOne(getIds("Armor")));
        return s;
    }

    private String potionsToString() {
        Potion[] potions = getPotions();
        String s = "Potions:\n" +
                "ID. Type | name | cost | attribute increase | required level\n";
        s += General.arrayToStringWithIds(potions, General.arrayPlusOne(getIds("Potion")));
        return s;
    }

    private String skillsToString() {
        String s = "ID. Type | name | cost | mana cost | damage | required level\n";
        s += General.arrayToStringWithIds(skills, General.intsInRange(1,skills.length+1));
        return s;
    }

    public void displayGoods() {
        String s = " *** Items *** ";
        s += weaponsToString();
        s += armorsToString();
        s += potionsToString();

        s +=  " *** Spells ***\n";
        s += skillsToString();
        System.out.println(s);
    }

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

}
