import java.util.*;
import java.lang.Math;

public class Map {
// class for the map
    private Cell[][] position;
    private int height;
    private int width;
    private int[] heroLocation;
    private static int maxDim = 1000;
    private static int defaultHeight = 8;
    private static int defaultWidth = 8;
    private static int symbolLength = 3;
    public static String legend = "X = your position, M = market, space = empty, I = inaccessible";
    private static int[] startingHeroLocation = {0,0};

    public Map(Cell[][] position, int height, int width) {
        setPosition(position);
        setHeight(height);
        setWidth(width);
    }

    public Map(int height, int width) {
        this(new Cell[height][width],height,width);
    }

    public Map() {
        this(defaultHeight,defaultWidth);
    }

    @Override
    public String toString() {
        String s = "";
        String str = "+"+"-".repeat(symbolLength);
        String rowDelimiter = str.repeat(width) + "+\n";
        for (int i = 0; i<height; i++) {
            s += rowDelimiter;
            for (int j = 0; j<width; j++) {
                s += "|"+padCell(position[i][j]);
            }
            s += "|\n";
        }
        s += rowDelimiter;
        return s;
    }

    private String padCell(Cell cell) {
        return " ".repeat(symbolLength/2) + cell + " ".repeat(symbolLength/2);
    }

    public void setRandomPosition(double marketProb, double emptyProb) {
        position = getPosition();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double r = Math.random();
                if (r < marketProb) {
                    position[i][j] = new Market();
                } else if (r < marketProb + emptyProb) {
                    position[i][j] = new EmptyCell();
                } else {
                    position[i][j] = new Inaccessible();
                }
            }
        }
        setPosition(position);
        setHeroLocation(startingHeroLocation);
    }

    public Cell getCell(int row, int col) {
        return position[row][col];
    }

    public Cell[][] getPosition() {
        return position;
    }

    private void setPosition(Cell[][] position) {
        this.position = position;
    }

    private void setCell(Cell cell, int row, int col) {
        Cell[][] map = getPosition();
        map[row][col] = cell;
        setPosition(map);
    }

    public int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        if(1 <= height && height <= maxDim ) {
            this.height = height;
        }
        else { throw new IllegalArgumentException("The height is too big or negative"); }
    }

    public int getWidth() {
        return width;
    }

    private void setWidth(int width) {
        if(1 <= width && width <= maxDim ) {
            this.width = width;
        }
        else { throw new IllegalArgumentException("The width is too big or negative"); }
    }

    public int[] getHeroLocation() {
        return heroLocation;
    }

    private void setHeroLocation(int[] heroLocation) {
        // performs no checks
        this.heroLocation = heroLocation;
        position[heroLocation[0]][heroLocation[1]].setHeroesHere(true);
    }

    public void moveHeroes(int[] heroLocation) {
        try {
            int r = heroLocation[0];
            int c = heroLocation[1];
            if (r < 0 || r >= height || c < 0 || c >= width) { throw new IllegalArgumentException("Location is out of bounds."); }
            else if (position[r][c].isInaccessible()) { throw new IllegalArgumentException("This location is inaccessible."); }
            else {
                position[this.heroLocation[0]][this.heroLocation[1]].setHeroesHere(false);
                this.heroLocation = heroLocation;
                position[r][c].setHeroesHere(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
