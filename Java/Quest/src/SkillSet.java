import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SkillSet implements Collection<Skill> {
    // the class for the skill set of a hero
    private List<Skill> skills;
    private static String legend = "ID. name | cost | mana cost | damage | required level";

    public SkillSet(Skill[] skills) {
        this.skills = Arrays.asList(skills);
    }

    public SkillSet() {
        skills = Arrays.asList(new Skill[0]);
    }

    @Override
    public int size() {
        return skills.size();
    }

    @Override
    public boolean isEmpty() {
        return skills.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return skills.contains(o);
    }

    @Override
    public Iterator<Skill> iterator() {
        return skills.iterator();
    }

    @Override
    public Object[] toArray() {
        return skills.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return skills.toArray(a);
    }

    @Override
    public boolean add(Skill skill) {
        return skills.add(skill);
    }

    @Override
    public boolean remove(Object o) {
        return skills.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return skills.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Skill> c) {
        return skills.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return skills.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return skills.retainAll(c);
    }

    @Override
    public void clear() {
        skills.clear();
    }

    public Skill[] getSkills() {
        return skills.toArray(new Skill[0]);
    }

    public void setSkills(Skill[] skills) {
        this.skills = Arrays.asList(skills);
    }

    public String toString() {
        String s = legend+"\n";
        return s+ General.myArrayToString(new List[]{skills});
    }

    public void addSkill(Skill skill) {
        // add skill to Skill Set
        Skill[] newSkills = new Skill[skills.size() + 1];
        newSkills[0] = skill;
        System.arraycopy(skills, 0, newSkills, 1, skills.size());
        setSkills(newSkills);
    }
}
