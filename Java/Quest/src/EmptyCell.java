public class EmptyCell extends Cell {
    public static String emptySymbol = " ";
    public EmptyCell() {
        super(emptySymbol);
    }

    public boolean isMarketCell() { return false; }
    public boolean isEmptyCell() { return true; }
    public boolean isInaccessibleCell() { return false; }
}
