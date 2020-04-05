import java.util.ArrayList;

public abstract class Hero extends  Unit {
    private int mp;
    private int currentMP;
    private Stats stats;
    private int experience;
    private Backpack backpack;
    private Weapon weapon;
    private Armor armor;
    private String className;
    private SkillSet skillSet;
    private static double damageMultiplier = 0.5;
    private static double dodgeChanceMultiplier = 0.02;
    private static double defenseMultiplier = 0.4;
    private static double dexteritySpellDamageMultiplier = 0.1;
    private static int levelMultiplier = 10;
    private static double lowLevelUpMultiplier = 0.05;
    private static double highLevelUpMultiplier = 0.1;
    private static double levelUpMPMultiplier = 0.1;

    public Hero(String name, int mp, Stats stats, int level, int experience,
                Backpack backpack, Weapon weapon, Armor armor, String className, SkillSet skills) {
        super(name, level, Unit.getHPLevelMultiplier()*level, Unit.getHPLevelMultiplier()*level);
        this.mp = mp;
        this.stats = stats;
        this.experience = experience;
        this.backpack = backpack;
        this.weapon = weapon;
        this.armor = armor;
        this.className = className;
        this.skillSet = skills;
        this.currentMP = mp;
    }

    public Hero(String name, int mp, Stats stats, int money, int experience, String className) {
        this(name, mp, stats, 1, experience, new Backpack(money), new Weapon(), new Armor(), className, new SkillSet());
    }

    public String toString() {
        return String.format("%s | %s | %d | %s | %d | %d", className, getName(), mp, stats, backpack.getMoney(), experience);
    }

    public String skillsToString() {
        return skillSet.toString();
    }

    public String detailedToString() {
        // detailed information about the hero
        String s = "~~~~~~\n";
        s += String.format("%s %s. Level: %d, HP: %d, mana: %d, experience: %d.\n", className, getName(), getLevel(), getCurrentHP(), mp, experience);
        s += stats.detailedToString()+"\n";
        s += "Weapon: "+weapon.detailedToString() + "\nArmor: "+armor.detailedToString()+"\n";
        s += "Backpack: \n"+backpack;
        s += "Skills: \n"+skillsToString();
        s+= "\n~~~~~~";
        return s;
    }

    public void tradeWithMarket(Market market) {
        System.out.println(getName()+" is trading with the market.");
        System.out.println(detailedToString());
        System.out.println("Are you buying an item (b,i), a spell (b,s) or selling (s)?");
        String ans = IOTools.getValidatedInput("b,i b,s s".split(" "));
        if (ans.equals("s")) {
            System.out.println("Enter the ID of the item you are selling.");
            int id = IOTools.getIntInput(1, getBackpack().getItems().length+1) - 1;
            sell(id, market);
        } else if (ans.equals("b,i")) {
            System.out.println("Enter the ID of the item you are buying.");
            int id = IOTools.getIntInput(1, market.getItems().length+1) - 1;
            buyItem(id, market);
        } else if (ans.equals("b,s")) {
            System.out.println("Enter the ID of the spell you are buying.");
            int id = IOTools.getIntInput(1, market.getSkills().length+1) - 1;
            buySpell(id, market);
        }
        System.out.println("Have a look at the hero after the trade.\n"+detailedToString());
    }

    public void buyItem(int itemID, Market market) {
        // hero buys item under ID itemID
        Item[] items = market.getItems();
        Item item = items[itemID];
        if (item.getCost() > getBackpack().getMoney()) { System.out.println("Not enough money"); }
        else if (item.getLevel() > getLevel()) { System.out.println("Level not high enough"); }
        else {
            getBackpack().addItem(items[itemID]);
            getBackpack().changeMoneyBy(-item.getCost());
        }
    }

    public void buySpell(int id, Market market) {
        // hero buys spell under ID itemID
        Skill[] skills = market.getSkills();
        Skill skill = skills[id];
        if (skill.getCost() > getBackpack().getMoney()) { System.out.println("Not enough money"); }
        else if (skill.getLevel() > getLevel()) { System.out.println("Level not high enough"); }
        else {
            getSkillSet().add(skill);
            getBackpack().changeMoneyBy(-skill.getCost());
        }
    }

    public void sell(int itemID, Market market) {
        // hero sells item under ID itemID (in hero's backpack) for twice less than the cost
        if (itemID >= 0 && itemID < getBackpack().getItems().length){
            Backpack backpack = getBackpack();
            backpack.changeMoneyBy(backpack.getItems()[itemID].getCost()/2);
            backpack.removeItem(itemID);
        }
    }

    public void drinkPotion(int itemID) {
        // drink the Potion
        Item[] items = backpack.getItems();
        if (itemID < 0 || itemID >= items.length) {
            System.out.println("This item ID does not exist. Quitting");
            return;
        }
        Item item = backpack.getItems()[itemID];
        if (!item.getType().equals("Potion")) {
            System.out.println("Not a potion! Quitting.");
            return;
        }
        Potion potion = (Potion) item;
        int attIncrease = potion.getAttIncrease();
        String potionName = potion.getName();
        switch (potionName) {
            case "Healing_Potion":
                changeCurrentHPBy(attIncrease);
                System.out.println("Drinking " + potionName);
                break;
            case "Strength_Potion":
                stats.changeStrengthBy(attIncrease);
                System.out.println("Drinking " + potionName);
                break;
            case "Magic_Potion":
                changeCurrentMPBy(attIncrease);
                System.out.println("Drinking " + potionName);
                break;
            case "Luck_Elixir":
                stats.changeDexterityBy(attIncrease);
                System.out.println("Drinking " + potionName);
                break;
            case "Mermaid_Tears":
                stats.changeAgilityBy(attIncrease);
                System.out.println("Drinking " + potionName);
                break;
            case "Ambrosia":
                stats.changeStrengthBy(attIncrease);
                stats.changeDexterityBy(attIncrease);
                stats.changeAgilityBy(attIncrease);
                System.out.println("Drinking " + potionName);
                break;
            default:
                System.out.println("Unknown potion. It does nothing.");
                break;
        }
        backpack.removeItem(itemID);
    }

    public static int getLowBounded(int b, int i) {
        return Math.max(i, b);
    }

    public static int getUpperBounded(int b, int i) {
        return Math.min(i, b);
    }

    public static int getBounded(int l, int u, int i) {
        int r = getLowBounded(l,i);
        return getUpperBounded(u,r);
    }

    public void changeCurrentHPBy(int amount) {
        setCurrentHP(getBounded(0,getHP(),amount+getCurrentHP()));
    }

    public void changeCurrentMPBy(int amount) {
        currentMP = getBounded(0,mp,amount+currentMP);
    }

    public void equip(int itemID) {
        // equip the item
        Item[] items = backpack.getItems();
        if (itemID < 0 || itemID >= items.length) { System.out.println("This item ID does not exist."); }
        else if (items[itemID].getType().equals("Potion")) { System.out.println("Cannot equip a potion!"); }
        else if (items[itemID].getType().equals("Weapon")) {
            equipWeapon((Weapon) items[itemID]);
        }
        else if (items[itemID].getType().equals("Armor")) {
            equipArmor((Armor) items[itemID]);
        }

    }
    
    private void equipWeapon(Weapon w) {
        // equip the weapon w
        backpack.removeItem(w);
        if (!weapon.isEmpty()) {
            backpack.addItem(weapon);
        }
        weapon = w;
    }
    
    private void equipArmor(Armor a) {
        // equip the armor a
        backpack.removeItem(a);
        if (!armor.isEmpty()) {
            backpack.addItem(armor);
        }
        armor = a;
    }

    public void unequipWeapon() {
        // unequip the current weapon
        equipWeapon(new Weapon());
    }

    public void unequipArmor() {
        // unequip the current armor
        equipArmor(new Armor());
    }

    public SkillSet getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(SkillSet skills) {
        this.skillSet = skills;
    }

    @Override
    public double getDamage() {
        return damageMultiplier*(stats.getStrength() + weapon.getDamage());
    }

    @Override
    public double getDefense() {
        return defenseMultiplier*armor.getDamageReduction();
    }

    @Override
    public double getDodgeChance() {
        return dodgeChanceMultiplier*stats.getAgility();
    }

    public void castSkill(Skill skill, Monster monster) {
        System.out.println(getName()+" uses "+ skill.getManaCost() +" mana to cast "+skill.getName()+" on "+monster.getName()+"!");
        int dealtDamage = (int) getSkillDamage(skill);
        monster.takeDamage(dealtDamage);
        System.out.println(getName()+" dealt "+dealtDamage+" damage to "+monster.getName());
        System.out.println(monster.getName()+"'s health is now "+monster.getCurrentHP());
        skill.doSideEffect(monster);
        setCurrentMP(getCurrentMP() - skill.getManaCost());
        System.out.println(getName()+" has "+getCurrentMP()+" mana left.");
    }

    public void gainExperience(int amount) {
        // gain amount of experience and level up if needed
        System.out.println(getName()+" gains "+amount+" experience.");
        experience = experience + amount;
        if (experience > getLevel()*levelMultiplier) {
            experience = experience - getLevel()*levelMultiplier;
            levelUp();
        }
    }

    public void levelUp() {
        System.out.println(getName()+" levels up!");
        setLevel(getLevel() + 1);
        setMp((int) (getMp()*(1+levelUpMPMultiplier)));
    }

    private double getSkillDamage(Skill skill) {
        return (skill.getDamage()+dexteritySpellDamageMultiplier*stats.getDexterity());
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getCurrentMP() {
        return currentMP;
    }

    public void setCurrentMP(int currentMP) {
        this.currentMP = currentMP;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public static double getDamageMultiplier() {
        return damageMultiplier;
    }

    public static void setDamageMultiplier(double damageMultiplier) {
        Hero.damageMultiplier = damageMultiplier;
    }

    public static double getDodgeChanceMultiplier() {
        return dodgeChanceMultiplier;
    }

    public static void setDodgeChanceMultiplier(double dodgeChanceMultiplier) {
        Hero.dodgeChanceMultiplier = dodgeChanceMultiplier;
    }

    public static double getDefenseMultiplier() {
        return defenseMultiplier;
    }

    public static void setDefenseMultiplier(double defenseMultiplier) {
        Hero.defenseMultiplier = defenseMultiplier;
    }

    public static double getDexteritySpellDamageMultiplier() {
        return dexteritySpellDamageMultiplier;
    }

    public static void setDexteritySpellDamageMultiplier(double dexteritySpellDamageMultiplier) {
        Hero.dexteritySpellDamageMultiplier = dexteritySpellDamageMultiplier;
    }

    public static int getLevelMultiplier() {
        return levelMultiplier;
    }

    public static void setLevelMultiplier(int levelMultiplier) {
        Hero.levelMultiplier = levelMultiplier;
    }

    public static double getLowLevelUpMultiplier() {
        return lowLevelUpMultiplier;
    }

    public static void setLowLevelUpMultiplier(double lowLevelUpMultiplier) {
        Hero.lowLevelUpMultiplier = lowLevelUpMultiplier;
    }

    public static double getHighLevelUpMultiplier() {
        return highLevelUpMultiplier;
    }

    public static void setHighLevelUpMultiplier(double highLevelUpMultiplier) {
        Hero.highLevelUpMultiplier = highLevelUpMultiplier;
    }

    public static double getLevelUpMPMultiplier() {
        return levelUpMPMultiplier;
    }

    public static void setLevelUpMPMultiplier(double levelUpMPMultiplier) {
        Hero.levelUpMPMultiplier = levelUpMPMultiplier;
    }
}
