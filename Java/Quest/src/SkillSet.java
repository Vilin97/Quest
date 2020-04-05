import java.util.*;

public class SkillSet implements Collection<Skill> {
    // the class for the skill set of a hero
    private List<Skill> skills;
    private static String legend = "ID. skill type | name | cost | mana cost | damage | required level";

    public SkillSet(Skill[] skills) {
        this.skills = Arrays.asList(skills);
    }

    public SkillSet() {
        skills = new ArrayList<>();
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
        return s+ General.myListToString(skills);
    }

    public void addSkill(Skill skill) {
        // add skill to Skill Set
        skills.add(skill);
    }
}
