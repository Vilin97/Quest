import java.io.File;  // Import the File class
import java.util.*;

public class ReadFile {
    // class to read and parse the files of heroes, monsters and items

    public static Hero[] readHeroData(String filename, String type) {
        List<Hero> heroes = new ArrayList<Hero>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    int MP = Integer.parseInt(data[1]);
                    int strength = Integer.parseInt(data[2]);
                    int agility = Integer.parseInt(data[3]);
                    int dexterity = Integer.parseInt(data[4]);
                    int money = Integer.parseInt(data[5]);
                    int experience = Integer.parseInt(data[6]);
                    Stats stats = new Stats(strength, agility, dexterity);
                    if (type.equals("W")) { Hero hero = new Warrior(name, MP, stats, money, experience); heroes.add(hero); }
                    else if (type.equals("S")) { Hero hero = new Sorcerer(name, MP, stats, money, experience); heroes.add(hero); }
                    else if (type.equals("P")) { Hero hero = new Paladin(name, MP, stats, money, experience); heroes.add(hero); }
                    else { throw new IllegalArgumentException("Wrong type (W, S, or P are supported)"); }
                }
                i ++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file "+filename);
            e.printStackTrace();
        }
        Hero[] res = new Hero[heroes.size()];
        res = heroes.toArray(res);
        return res;
    }

    public static Weapon[] readWeaponData(String filename) {
        List<Weapon> weapons = new ArrayList<Weapon>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    // Name/cost/level/damage/required hands
                    int cost = Integer.parseInt(data[1]);
                    int level = Integer.parseInt(data[2]);
                    int damage = Integer.parseInt(data[3]);
                    int hands = Integer.parseInt(data[4]);
                    Weapon weapon = new Weapon(name, cost, level, damage, hands);
                    weapons.add(weapon);
                }
                i ++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file "+filename);
            e.printStackTrace();
        }
        Weapon[] res = new Weapon[weapons.size()];
        res = weapons.toArray(res);
        return res;
    }

    public static Armor[] readArmorData(String filename) {
        List<Armor> armors = new ArrayList<Armor>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    // Name/cost/required level/damage reduction
                    int cost = Integer.parseInt(data[1]);
                    int level = Integer.parseInt(data[2]);
                    int damageReduction = Integer.parseInt(data[3]);
                    Armor armor = new Armor(name, cost, damageReduction, level);
                    armors.add(armor);
                }
                i ++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file "+filename);
            e.printStackTrace();
        }
        Armor[] res = new Armor[armors.size()];
        res = armors.toArray(res);
        return res;
    }

    public static Potion[] readPotionData(String filename) {
        List<Potion> potions = new ArrayList<Potion>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    // Name/cost/required level/attribute increase
                    int cost = Integer.parseInt(data[1]);
                    int level = Integer.parseInt(data[2]);
                    int attIncrease = Integer.parseInt(data[3]);
                    Potion potion = new Potion(name, cost, attIncrease, level);
                    potions.add(potion);
                }
                i ++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file "+filename);
            e.printStackTrace();
        }
        Potion[] res = new Potion[potions.size()];
        res = potions.toArray(res);
        return res;
    }

    public static Monster[] readMonsterData(String filename, String type) {
        List<Monster> monsters = new ArrayList<Monster>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    // Name/level/damage/defense/dodge chance
                    int level = Integer.parseInt(data[1]);
                    int damage = Integer.parseInt(data[2]);
                    int defense = Integer.parseInt(data[3]);
                    int dodgeChance = Integer.parseInt(data[4]);
                    if (type.equals("D")) {Monster monster = new Dragon(name, level, damage, defense, dodgeChance);monsters.add(monster);}
                    else if (type.equals("S")) {Monster monster = new Spirit(name, level, damage, defense, dodgeChance);monsters.add(monster);}
                    else if (type.equals("E")) {Monster monster = new Exoskeleton(name, level, damage, defense, dodgeChance);monsters.add(monster);}
                    else { throw new IllegalArgumentException("Wrong type (D, S, or E are supported)"); }
                }
                i ++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file "+filename);
            e.printStackTrace();
        }
        Monster[] res = new Monster[monsters.size()];
        res = monsters.toArray(res);
        return res;
    }

    public static Skill[] readSkillData(String filename, String type) {
        List<Skill> skills = new ArrayList<Skill>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    // Name/cost/required level/damage/mana cost
                    String name = data[0];
                    int cost = Integer.parseInt(data[1]);
                    int level = Integer.parseInt(data[2]);
                    int damage = Integer.parseInt(data[3]);
                    int manaCost = Integer.parseInt(data[4]);
                    if (type.equals("F")) {Skill skill = new FireSpell(name, cost, level, damage, manaCost);skills.add(skill);}
                    else if (type.equals("I")) {Skill skill = new IceSpell(name, cost, level, damage, manaCost);skills.add(skill);}
                    else if (type.equals("L")) {Skill skill = new LightningSpell(name, cost, level, damage, manaCost);skills.add(skill);}
                    else { throw new IllegalArgumentException("Wrong type (F, I, or L are supported)"); }
                }
                i ++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file "+filename);
            e.printStackTrace();
        }
        Skill[] res = new Skill[skills.size()];
        res = skills.toArray(res);
        return res;
    }

}