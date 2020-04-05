import java.util.*;

public class Backpack implements Collection<Item> {
    // class for hero's inventory
    private int money;
    private List<Item> items;

    public Backpack(int money, Item[] items) {
        this.money = money;
        this.items = Arrays.asList(items);
    }

    public Backpack(int money) {
        this(money, new Item[0]);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return items.contains(o);
    }

    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }

    @Override
    public Object[] toArray() {
        return items.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return items.toArray(a);
    }

    @Override
    public boolean add(Item item) {
        return items.add(item);
    }

    @Override
    public boolean remove(Object o) {
        return items.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return items.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Item> c) {
        return items.containsAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return items.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return items.retainAll(c);
    }

    @Override
    public void clear() {
        setMoney(0);
        items.clear();
    }


    public int[] getItemIDs() {
        // get the IDs (indices) of the items starting with 1
        return General.intsInRange(1,size()+1);
    }

    public Weapon[] getWeapons() {
        int[] ids = getIds("Weapon");
        Weapon[] res = new Weapon[ids.length];
        for (int i = 0; i < ids.length; i++) {
            res[i] = (Weapon) items.get(ids[i]);
        }
        return res;
    }

    public Armor[] getArmors() {
        int[] ids = getIds("Armor");
        Armor[] res = new Armor[ids.length];
        for (int i = 0; i < ids.length; i++) {
            res[i] = (Armor) items.get(ids[i]);
        }
        return res;
    }

    public Potion[] getPotions() {
        int[] ids = getIds("Potion");
        Potion[] res = new Potion[ids.length];
        for (int i = 0; i < ids.length; i++) {
            res[i] = (Potion) items.get(ids[i]);
        }
        return res;
    }

    public int[] getIds(String type) {
        int num = getCount(type);
        int[] ids = new int[num];
        int j = 0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getType().equals(type)) {
                ids[j] = i;
                j ++;
            }
        }
        return ids;
    }

    private int getCount(String type) {
        int res = 0;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getType().equals(type)) { res++; }
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
        Item[] newItems = new Item[items.size() + 1];
        newItems[0] = item;
        System.arraycopy(items, 0, newItems, 1, items.size());
        setItems(newItems);
    }

    public void removeItem(Item item) {
        int itemID = getID(item);
        removeItem(itemID);
    }

    private int getID(Item item) {
        // returns -1 if item is not in the backpack
        int id = -1;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equals(item)) {
                id = i;
            }
        }
        return id;
    }

    public void removeItem(int itemID) {
        // does nothing if id is out of bounds
        if (itemID < items.size() && itemID >= 0) {
            Item[] newItems = new Item[items.size() - 1];
            System.arraycopy(items, 0, newItems, 0, itemID);
            for (int i = itemID+1; i < items.size(); i++) {
                newItems[i-1] = items.get(i);
            }
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
        return toArray(new Item[0]);
    }

    public void setItems(Item[] items) {
        this.items = Arrays.asList(items);
    }
}
