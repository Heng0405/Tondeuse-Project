package actions;

import exceptions.OutOfBoundsException;
import models.Action;
import models.Tondeuse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Parcourir {
    List<Action> actions;
    Tondeuse tondeuse;
    static final Logger LOGGER = LoggerFactory.getLogger(Parcourir.class);
    public Parcourir(List<Action> actions, Tondeuse tondeuse){
        this.actions = actions;
        this.tondeuse = tondeuse;
    }

    public Tondeuse passerTondeuse(List<Action> actions, Tondeuse tondeuse) throws OutOfBoundsException {
        for(int i=0; i<actions.size();i++){
            tondeuse = actions.get(i).execute(tondeuse);

        } LOGGER.info("Actions exécutées)");
        return tondeuse;
    }






}
