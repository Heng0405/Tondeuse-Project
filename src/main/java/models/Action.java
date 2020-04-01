package models;

import exceptions.OutOfBoundsException;
import exceptions.ParsingException;
import models.TailleDePelouse;
import models.Tondeuse;
import utils.ReadCommandFile;

public abstract class Action {
    public final static int minimumX = 0;
    public final static int minimumY = 0;

    protected TailleDePelouse coinSuperieur;
    public Action(TailleDePelouse coinSuperieur){
        this.coinSuperieur = coinSuperieur;
    }

    public abstract Tondeuse execute(Tondeuse tondeuse) throws OutOfBoundsException, ParsingException;

}
