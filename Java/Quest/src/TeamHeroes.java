public class TeamHeroes extends Team {
    private static String legend =
            "ID | class | name | mana | strength | agility | dexterity | money | experience";

    public TeamHeroes() {
    }

    public TeamHeroes(Hero[] heroes) {
        super(heroes);
    }

    public String toString()  {
        return "Team:\n" + legend +"\n"+ General.myArrayToString(getUnits());
    }

    /*public void changeTeamMP(int amount){
        for (Unit h : this ) {
            Hero hero = (Hero) h;
            hero.setCurrentMP();
        }
    }
    */
}
