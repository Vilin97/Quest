public class TeamMonsters extends Team {
    private static String legend =
            "ID | class | name | level | damage | defense | dodgeChance";
    public TeamMonsters() {
    }

    public TeamMonsters(Unit[] units) {
        super(units);
    }

    @Override
    public String toString() {
        return "Team:\n" + legend +"\n"+ General.myArrayToString(getUnits());
    }
}
