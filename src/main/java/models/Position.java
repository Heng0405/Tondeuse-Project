package models;

import exceptions.OutOfBoundsException;

public class Position {

    private int x;   // x>=0
    private int y;   // y>=0


    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Position(int x, int y) throws OutOfBoundsException {
        if (x < 0 || y < 0) {
            throw new OutOfBoundsException("Position is out of bound");
        }
        this.x = x;
        this.y = y;
    }

    public void setX(int x) throws OutOfBoundsException {
        if (x < 0 || y < 0) {
            throw new OutOfBoundsException("Position is out of bound");
        }
        this.x = x;
    }

    public void setY(int y) throws OutOfBoundsException {
        if (x < 0 || y < 0) {
            throw new OutOfBoundsException("Position is out of bound");
        }
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Position other = (Position) obj;
        if (x != other.x) {
            return false;
        }
        if (y != other.y) {
            return false;
        }
        return true;

    }
}
