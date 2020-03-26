import java.util.Arrays;

public class Backpack {
    // class for hero's inventory
    private int money;
    private Item[] items;

    public Backpack(int money, Item[] items) {
        this.money = money;
        this.items = items;
    }

    public Backpack(int money) {
        this(money, new Item[0]);
    }

    public Weapon[] getWeapons() {
        int[] ids = getIds("Weapon");
        Weapon[] res = new Weapon[ids.length];
        for (int i = 0; i < ids.length; i++) {
            res[i] = (Weapon) items[ids[i]];
        }
        return res;
    }

    public Armor[] getArmors() {
        int[] ids = getIds("Armor");
        Armor[] res = new Armor[ids.length];
        for (int i = 0; i < ids.length; i++) {
            res[i] = (Armor) items[ids[i]];
        }
        return res;
    }

    public Potion[] getPotions() {
        int[] ids = getIds("Potion");
        Potion[] res = new Potion[ids.length];
        for (int i = 0; i < ids.length; i++) {
            res[i] = (Potion) items[ids[i]];
        }
        return res;
    }

    public int[] getIds(String type) {
        int num = getCount(type);
        int[] ids = new int[num];
        int j = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getType().equals(type)) {
                ids[j] = i;
                j ++;
            }
        }
        return ids;
    }

    private int getCount(String type) {
        int res = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i].getType().equals(type)) { res++; }
        }
        return res;
    }

    public String itemsToString() {
        Weapon[] weapons = getWeapons();
        Armor[] armors = getArmors();
        Potion[] potions = getPotions();
        String s = "";
        s += "Weapons:\n" +
                "ID. Type | name | cost | required hands | damage | required level\n";
        s += General.arrayToStringWithIds(weapons, General.arrayPlusOne(getIds("Weapon")));
        s += "Armor:\n" +
                "ID. Type | name | cost | damage reduction | required level\n";
        s += General.arrayToStringWithIds(armors, General.arrayPlusOne(getIds("Armor")));
        s += "Potions:\n" +
                "ID. Type | name | cost | attribute increase | required level\n";
        s += General.arrayToStringWithIds(potions, General.arrayPlusOne(getIds("Potion")));
        return s;
    }


    @Override
    public String toString() {
        String s = "";
        s += "Money: "+money+".\n";
        s += itemsToString();
        return s;
    }

    public void addItem(Item item) {
        // add item to backpack
        Item[] newItems = new Item[items.length + 1];
        newItems[0] = item;
        System.arraycopy(items, 0, newItems, 1, items.length);
        setItems(newItems);
    }

    public void removeItem(Item item) {
        int itemID = getID(item);
        removeItem(itemID);
    }

    private int getID(Item item) {
        // returns -1 if item is not in the backpack
        int id = -1;
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(item)) {
                id = i;
            }
        }
        return id;
    }

    public void removeItem(int itemID) {
        // does nothing if id is out of bounds
        if (itemID < items.length && itemID >= 0) {
            Item[] newItems = new Item[items.length - 1];
            System.arraycopy(items, 0, newItems, 0, itemID);
            if (items.length - itemID + 1 >= 0)
                System.arraycopy(items, itemID + 1, newItems, itemID, items.length - itemID + 1);
            setItems(newItems);
        }
    }

    public void changeMoneyBy(int amount) {
        money = money + amount;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }
}
