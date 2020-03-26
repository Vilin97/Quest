public class FireSpell extends Skill {
    private static double defenseReductionMultiplier=0.1;

    public FireSpell(String name, int cost, int level, int damage, int manaCost) {
        super(name, cost, level, damage, manaCost, "FireSpell");
    }

    public void doSideEffect(Monster monster) {
        monster.setDefense((int) (monster.getDefense()*(1-defenseReductionMultiplier)));
        System.out.println(monster.getName()+"'s defense was reduced to "+monster.getDefense());
    }
}
