public class EmptyCell extends Cell {
    public static String emptySymbol = " ";
    public EmptyCell() {
        super(emptySymbol);
    }

    public boolean isMarket() { return false; }
    public boolean isEmpty() { return true; }
    public boolean isInaccessible() { return false; }
}
