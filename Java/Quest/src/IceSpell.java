public class IceSpell extends Skill {
    private static double damageReductionMultiplier=0.1;
    public IceSpell(String name, int cost, int level, int damage, int manaCost) {
        super(name, cost, level, damage, manaCost, "IceSpell");
    }

    @Override
    public void doSideEffect(Monster monster) {
        monster.setDamage((int) (monster.getDamage()*(1-damageReductionMultiplier)));
        System.out.println(monster.getName()+"'s damage was reduced to "+monster.getDamage());
    }
}
