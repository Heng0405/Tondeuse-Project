package models;

public class TailleDePelouse {
    public  int maxX;
    public  int maxY;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + maxX;
        result = prime * result + maxY;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TailleDePelouse other = (TailleDePelouse) obj;
        if (maxX != other.maxX)
            return false;
        if (maxY != other.maxY)
            return false;
        return true;
    }
}
