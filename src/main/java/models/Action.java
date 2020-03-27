package models;

import exceptions.OutOfBoundsException;
import models.TailleDePelouse;
import models.Tondeuse;

public abstract class Action {
    public final static int minimumX = 0;
    public final static int minimumY = 0;

    TailleDePelouse coinSuperieur;
    public Action(final TailleDePelouse coinSuperieur){
        this.coinSuperieur = coinSuperieur;
    }

    public abstract void execute(Tondeuse tondeuse) throws OutOfBoundsException;

}
