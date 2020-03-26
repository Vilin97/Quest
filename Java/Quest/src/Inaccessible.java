public class Inaccessible extends Cell {
    public static String inaccessibleSymbol = "I";
    public Inaccessible() {
        super(inaccessibleSymbol);
    }

    public boolean isMarket() { return false; }
    public boolean isEmpty() { return false; }
    public boolean isInaccessible() { return true; }
}
