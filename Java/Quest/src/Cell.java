public abstract class Cell {
    // a class for a tile on a board
    private String symbol;
    private static String heroesSymbol = "X";
    private boolean heroesHere;

    public Cell(String symbol) {
        setSymbol(symbol);
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isHeroesHere() {
        return heroesHere;
    }

    public void setHeroesHere(boolean heroesHere) {
        this.heroesHere = heroesHere;
    }

    public boolean equals(Cell cell) {
        return symbol.equals(cell.symbol);
    }

    public String toString() {
        String s;
        if(heroesHere) {
            s = heroesSymbol;
        }
        else {
            s = symbol;
        }
        return s;
    }
    public abstract boolean isMarketCell();
    public abstract boolean isInaccessibleCell();
    public abstract boolean isEmptyCell();
}
