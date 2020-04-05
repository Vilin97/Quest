public class Inaccessible extends Cell {
    public static String inaccessibleSymbol = "I";
    public Inaccessible() {
        super(inaccessibleSymbol);
    }

    public boolean isMarketCell() { return false; }
    public boolean isEmptyCell() { return false; }
    public boolean isInaccessibleCell() { return true; }
}
