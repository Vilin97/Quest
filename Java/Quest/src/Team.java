import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Team {
    private Unit[] units;

    public Team() {
    }

    public Team(Unit[] units) {
        this.units = units;
    }

    @Override
    public abstract String toString();

    public Unit[] getUnits() {
        return units;
    }

    public Unit[] getAliveUnits() {
        List<Unit> alive = new ArrayList<Unit>();
        for (int i = 0; i < units.length; i++) {
            if (units[i].getCurrentHP() > 0){ alive.add(units[i]); }
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
        for (int i = 0; i < units.length; i++) {
            units[i].setCurrentHP(0);
        }
    }

    public void setUnits(Unit[] units) {
        this.units = units;
    }

    public boolean teamDead() {
        boolean res = true;
        for (int i = 0; i < units.length; i++) {
            if (units[i].getCurrentHP() > 0) {
                res = false;
            }
        }
        return res;
    }

    public int getHighestLevel() {
        // return the highest level in the team
        int res = -1;
        for (int i = 0; i < units.length; i++) {
            if (units[i].getLevel() > res) {
                res = units[i].getLevel();
            }
        }
        return res;
    }
}
