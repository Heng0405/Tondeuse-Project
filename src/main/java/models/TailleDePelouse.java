package models;

public class TailleDePelouse {
    public static int maxX;
    public static int maxY;

    public TailleDePelouse(int maxX, int maxY){
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }
}
