package actions;

import exceptions.OutOfBoundsException;
import exceptions.ParsingException;
import models.Action;
import models.Tondeuse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parcourir {
    List<Action> actions;
    Tondeuse tondeuse;
    static final Logger LOGGER = LoggerFactory.getLogger(Parcourir.class);
    public Parcourir(List<Action> actions, Tondeuse tondeuse){
        this.actions = actions;
        this.tondeuse = tondeuse;
    }

    public Tondeuse passerTondeuse(List<Action> actions, Tondeuse tondeuse) throws OutOfBoundsException, ParsingException {
        for(int i=0; i<actions.size();i++){
            tondeuse = actions.get(i).execute(tondeuse);

        } LOGGER.info("Actions exécutées)");
        return tondeuse;
    }

    /**
     *
     * @param map un map qui contient des tondeuses et les commandes.
     * @return une liste de tondeuses après avoir passée les commandes.
     * @throws OutOfBoundsException
     */
    public List<Tondeuse> passerCommands(Map<Tondeuse, ArrayList<Action>> map) throws OutOfBoundsException, ParsingException {
        List<Tondeuse> tondeuses = new ArrayList<Tondeuse>();
        for (Map.Entry<Tondeuse, ArrayList<Action>> entry : map.entrySet()) {
            for(int i=0;i<entry.getValue().size();i++){
                Tondeuse tondeuse = entry.getValue().get(i).execute(entry.getKey());
            }tondeuses.add(tondeuse);
        }return tondeuses;
    }

    /**
     *
     * @param tondeuses
     */
    public void printResult(List<Tondeuse> tondeuses){
        for(Tondeuse tondeuse : tondeuses){
            System.out.println("The final position of this tondeuse : "+ tondeuse.getPosition().getX()+","+tondeuse.getPosition().getY());
            System.out.println("The final position of this tondeuse : "+ tondeuse.getOrientation());
        }
    }





}
