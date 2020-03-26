public class TeamHeroes extends Team {
    private static String legend =
            "ID | class | name | mana | strength | agility | dexterity | money | experience";

    public TeamHeroes() {
    }

    public TeamHeroes(Unit[] units) {
        super(units);
    }

    public String toString()  {
        return "Team:\n" + legend +"\n"+ General.myArrayToString(getUnits());
    }
}
