import java.util.Objects;

public abstract class Item {
    // class for items
    private String name;
    private int cost;
    private int level;
    private String type;

    public Item(String name, int cost, int level, String type) {
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.type = type;
    }

    @Override
    public abstract String toString();

    public String getType() {
        return type;
    }

    public boolean equals(Item item) {
        if (this == item) return true;
        return cost == item.cost &&
                level == item.level &&
                name.equals(item.name) &&
                type.equals(item.type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setType(String type) {
        this.type = type;
    }
}
