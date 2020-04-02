package models;

import java.util.List;

public class Tondeuse {

    public enum Orientation {
        N, E, W, S
    }

    @Override
    public String toString() {
        return "Tondeuse{" +
                "position=" + position +
                ", orientation=" + orientation +
          //      ", listActions=" + listActions +
                '}';
    }

    /**
     * Position of tondeuse.
     */
    private Position position;

    /**
     * Orientation of tondeuse.
     */
    private Orientation orientation;

    /**
     * Actions à executer.
     */
    //private List<Action> listActions;


    public void setPosition(Position position) {
        this.position = position;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Tondeuse(Position position, Orientation orientation){
        this.position = position;
        this.orientation = orientation;
    }


}
