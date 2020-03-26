public class LightningSpell extends Skill {
    private static double dodgeChanceReductionMultiplier=0.05;

    public LightningSpell(String name, int cost, int level, int damage, int manaCost) {
        super(name, cost, level, damage, manaCost, "LightningSpell");
    }

    @Override
    public void doSideEffect(Monster monster) {
        monster.setDodgeChance((int) (monster.getDodgeChance()*(1-dodgeChanceReductionMultiplier)));
        System.out.println(monster.getName()+"'s dodge chance was reduced to "+monster.getDodgeChance());
    }
}
