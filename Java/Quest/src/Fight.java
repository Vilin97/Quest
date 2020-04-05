
public class Fight {
    private TeamHeroes heroes;
    private TeamMonsters monsters;
    private boolean fightOver;
    private boolean heroesWon;
    private boolean monstersWon;
    private static final String[] actionKeys = {"a", "c", "d", "s", "q"};
    private static final String legend = actionKeys[0]+" = attack; "+actionKeys[1]+" = cast spell;\n"
            + actionKeys[3]+" = switch weapon/armor or drink potion; "+actionKeys[4]+" = retire from the fight (same as losing the fight).";
    private static final int victoryMoneyReward = 150;
    private static final int victoryExpReward = 2;
    private static final double regenerateHPMultiplier = 0.05;
    private static final double regenerateMPMultiplier = 0.05;

    public Fight(TeamHeroes heroes, TeamMonsters monsters, boolean fightOver, boolean heroesWon, boolean monstersWon) {
        this.heroes = heroes;
        this.monsters = monsters;
        this.fightOver = fightOver;
        this.heroesWon = heroesWon;
        this.monstersWon = monstersWon;
    }

    public Fight(TeamHeroes heroes, TeamMonsters monsters, boolean fightOver) {
        this(heroes,monsters,fightOver, false, false);
    }

    public Fight(TeamHeroes heroes, TeamMonsters monsters) {
        this(heroes,monsters,false);
    }


    public void fight() {
        // method for fighting the fight
        while (!fightOver) {
            doOneRound();
        }
        if (heroesWon) { processFightWon(); }
        else if (monstersWon) { processFightLost(); }
    }

    private void processFightLost() {
        System.out.println("The fight is lost. As well as half of heroes' gold.");
        for (Unit h : heroes.getUnits()) {
            Hero hero = (Hero) h;
            hero.getBackpack().setMoney(hero.getBackpack().getMoney()/2);
            hero.setCurrentHP(hero.getHP()/2);
        }
    }

    private void processFightWon() {
        for (Unit h : heroes.getUnits()) {
            Hero hero = (Hero) h;
            hero.getBackpack().setMoney(hero.getBackpack().getMoney() + victoryMoneyReward);
            hero.gainExperience(victoryExpReward);
        }
        System.out.println("Congratulations! The victory is yours!");
    }

    private void doOneRound() {
        // process one round of fighting
        // the opponents are chosen randomly
        Unit[] aliveHeroes = heroes.getAliveUnits();
        System.out.println("\n        ******\nHeroes' turn to attack!");
        for (int i = 0; i < aliveHeroes.length; i++) {
            checkFightOver();
            if (fightOver) { return; }
            Hero hero = (Hero) aliveHeroes[i];
            Monster monster = (Monster) monsters.getRandomAliveUnit();
            processHeroesTurn(hero,monster);
            if (!monster.isAlive()) {
                hero.gainExperience(monster.getLevel());
            }
        }
        checkFightOver();
        if (fightOver) { return; }
        System.out.println("\n        ******\nMonster' turn to attack!");
        Unit[] aliveMonsters = monsters.getAliveUnits();
        for (int i = 0; i < aliveMonsters.length; i++) {
            checkFightOver();
            if (fightOver) { return; }
            Hero hero = (Hero) heroes.getRandomAliveUnit();
            Monster monster = (Monster) aliveMonsters[i];
            processMonstersTurn(hero,monster);
        }
        heroesRegenerate();
    }

    private void heroesRegenerate() {
        System.out.println("Heroes regenerate.");
        for (Unit h : heroes.getUnits()) {
            Hero hero = (Hero) h;
            hero.setCurrentHP((int) (hero.getCurrentHP() + hero.getHP()*regenerateHPMultiplier));
            hero.setCurrentMP((int) (hero.getCurrentMP() + hero.getMp() *regenerateMPMultiplier));
        }
    }

    private void checkFightOver() {
        // set the flags
        boolean monstersDead = monsters.teamDead();
        boolean heroesDead = heroes.teamDead();
        heroesWon = monstersDead;
        monstersWon = heroesDead;
        fightOver = heroesWon || monstersWon;
    }

    private void processMonstersTurn(Hero hero, Monster monster) {
        monster.attack(hero);
    }

    private void processHeroesTurn(Hero hero, Monster monster) {
        String name = hero.getName();
        System.out.println("It's "+name+"'s turn. Here is the information about "+name+":\n"+hero.detailedToString());
        System.out.println("Here is the information about "+name+"'s opposing monster:\n"+monster.detailedToString());
        processHeroAction(hero, monster);
    }
    private void processHeroAction(Hero hero, Monster monster) {
        String name = hero.getName();
        System.out.println("Choose what "+name+" does. "+legend);
        String ans = IOTools.getValidatedInput(actionKeys);
        if (ans.equals(actionKeys[0])) {
            hero.attack(monster);
        } else if (ans.equals(actionKeys[1])) {
            if (hero.getSkillSet().getSkills().length == 0) {
                System.out.println("There are no spells to cast. Choose another action.");
                processHeroAction(hero,monster);
            }
            System.out.println("Here are the spells available: "+hero.skillsToString());
            System.out.println("Choose the spell to use by entering its ID:");
            int id = IOTools.getIntInput(1,hero.getSkillSet().getSkills().length+1)-1;
            Skill skill = hero.getSkillSet().getSkills()[id];
            if (skill.getManaCost() > hero.getCurrentMP()) {
                System.out.println("Not enough mana. Choose another action.");
                processHeroAction(hero,monster);
            } else { hero.castSkill(skill, monster); }
        } else if (ans.equals(actionKeys[3])) {
            Quest.alterHeroOnce(hero);
        } else if (ans.equals(actionKeys[4])) {
            heroes.killTeam();
        } else { processHeroAction(hero, monster);}
    }

    public TeamHeroes getHeroes() {
        return heroes;
    }

    public void setHeroes(TeamHeroes heroes) {
        this.heroes = heroes;
    }

    public TeamMonsters getMonsters() {
        return monsters;
    }

    public void setMonsters(TeamMonsters monsters) {
        this.monsters = monsters;
    }

    public boolean isFightOver() {
        return fightOver;
    }

    public void setFightOver(boolean fightOver) {
        this.fightOver = fightOver;
    }

    public boolean isHeroesWon() {
        return heroesWon;
    }

    public void setHeroesWon(boolean heroesWon) {
        this.heroesWon = heroesWon;
    }

    public boolean isMonstersWon() {
        return monstersWon;
    }

    public void setMonstersWon(boolean monstersWon) {
        this.monstersWon = monstersWon;
    }
}
