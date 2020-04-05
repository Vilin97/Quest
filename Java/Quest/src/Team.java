import java.util.*;

public abstract class Team<U extends Unit> implements Collection<U> {
    private List<U> units;

    public Team() {
    }

    public Team(Unit[] units) {
        this.units = (List<U>) Arrays.asList(units);
    }

    @Override
    public int size() {
        return units.size();
    }

    @Override
    public boolean isEmpty() {
        return units.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return units.contains(o);
    }

    @Override
    public Iterator<U> iterator() {
        return units.iterator();
    }

    @Override
    public Object[] toArray() {
        return units.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return units.toArray(a);
    }

    @Override
    public boolean add(U unit) {
        return units.add(unit);
    }

    @Override
    public boolean remove(Object o) {
        return units.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return units.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends U> c) {
        return units.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return units.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return units.retainAll(c);
    }

    @Override
    public void clear() {
        units.clear();
    }

    @Override
    public abstract String toString();

    public Unit[] getUnits() {
        return units.toArray(new Unit[0]);
    }

    public int[] getUnitIDs() {
        // get the IDs (indices) of the units starting with 1
        return General.intsInRange(1,units.size()+1);
    }

    public void changeTeamHP(int amount){
        for( Unit u : units) {
            u.setCurrentHP(u.getCurrentHP() + amount);
        }
    }

    public Unit[] getAliveUnits() {
        List<Unit> alive = new ArrayList<Unit>();
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getCurrentHP() > 0){ alive.add(units.get(i)); }
        }
        Unit[] res = new Unit[alive.size()];
        res = alive.toArray(res);
        return res;
    }

    public Unit getRandomAliveUnit() {
        // returns random alive unit
        Unit[] alive = getAliveUnits();
        int id = new Random().nextInt(alive.length);
        return alive[id];
    }

    public void killTeam() {
        for (int i = 0; i < units.size(); i++) {
            units.get(i).setCurrentHP(0);
        }
    }

    public void setUnits(Unit[] units) {
        this.units = (List<U>) Arrays.asList(units);
    }

    public boolean teamDead() {
        boolean res = true;
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getCurrentHP() > 0) {
                res = false;
            }
        }
        return res;
    }

    public int getHighestLevel() {
        // return the highest level in the team
        int res = -1;
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getLevel() > res) {
                res = units.get(i).getLevel();
            }
        }
        return res;
    }
}
