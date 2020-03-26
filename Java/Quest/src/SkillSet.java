public class SkillSet {
    // the class for the skill set of a hero
    private Skill[] skills;
    private static String legend = "ID. name | cost | mana cost | damage | required level";

    public SkillSet(Skill[] skills) {
        this.skills = skills;
    }

    public SkillSet() {
        skills = new Skill[0];
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }

    public String toString() {
        String s = legend+"\n";
        return s+ General.myArrayToString(skills);
    }

    public void addSkill(Skill skill) {
        // add skill to Skill Set
        Skill[] newSkills = new Skill[skills.length + 1];
        newSkills[0] = skill;
        System.arraycopy(skills, 0, newSkills, 1, skills.length);
        setSkills(newSkills);
    }
}
