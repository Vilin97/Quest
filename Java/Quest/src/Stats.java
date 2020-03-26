public class Stats {
    // class to hold hero stats
    private int strength;
    private int dexterity;
    private int agility;

    public Stats(int strength, int dexterity, int agility) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.agility = agility;
    }

    @Override
    public String toString() {
        return  String.format("%d | %d | %d", strength, dexterity, agility);
    }

    public String detailedToString() {
        return String.format("Strength: %d, Dexterity: %d, Agility: %d.",strength,dexterity,agility);
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getAgility() {
        return agility;
    }

    public void changeStrengthBy(int attIncrease) {
        strength = Hero.getLowBounded(0,strength + attIncrease);
    }

    public void changeDexterityBy(int attIncrease) {
        dexterity = Hero.getLowBounded(0,dexterity + attIncrease);
    }

    public void changeAgilityBy(int attIncrease) {
        agility = Hero.getLowBounded(0,agility + attIncrease);
    }

}
