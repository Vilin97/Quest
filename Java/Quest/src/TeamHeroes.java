public class TeamHeroes extends Team<Hero> {
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

    public void regenerateTeamMP(double regenCoefficient){
        for (Hero hero: this ) {
            hero.setCurrentMP((int) (hero.getCurrentMP() + hero.getMp()*regenCoefficient));
        }
    }
}
