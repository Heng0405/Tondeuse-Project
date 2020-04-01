package actions;

import exceptions.OutOfBoundsException;
import exceptions.ParsingException;
import models.Action;
import models.Position;
import models.TailleDePelouse;
import models.Tondeuse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoDroite extends Action {
    static final Logger LOGGER = LoggerFactory.getLogger(GoAvance.class);

    public GoDroite(final TailleDePelouse coinSuperieur){
        super(coinSuperieur);
    }


    public Tondeuse execute(Tondeuse tondeuse) throws ParsingException {
        LOGGER.info("Tondeuse prêt à tourner à droite!");
        LOGGER.info("Position initiale : (" + tondeuse.getPosition().getX() + "," + tondeuse.getPosition().getY() + ")");
        LOGGER.info("Orientation initiale {}", tondeuse.getOrientation());


        switch (tondeuse.getOrientation()) {
            case N:
                tondeuse.setOrientation(Tondeuse.Orientation.E);
                break;
            case W:
                tondeuse.setOrientation(Tondeuse.Orientation.N);
                break;
            case E:
                tondeuse.setOrientation(Tondeuse.Orientation.S);
                break;
            case S:
                tondeuse.setOrientation(Tondeuse.Orientation.W);
                break;
            default:
                throw new ParsingException("Orientation non connue.");

        }

        LOGGER.info("Action terminée!");
        LOGGER.info("Position finale ({} {})", tondeuse.getPosition().getX(), tondeuse.getPosition().getY());
        LOGGER.info("Orientation finale {}", tondeuse.getOrientation());
        return tondeuse;
    }






/*    private Tondeuse southGoDroite(Tondeuse tondeuse) {
        Position  position = tondeuse.getPosition();
        Tondeuse.Orientation orientation = tondeuse.getOrientation();
        int xInitale = position.getX();
        if (xInitale > Action.minimumX) {
            LOGGER.info("Action autorisée");
            try {
                position.setX(xInitale - 1);
                orientation = Tondeuse.Orientation.W;
            } catch (OutOfBoundsException e) {
                LOGGER.error("Action non autorisé");
            }
        }
        return new Tondeuse(position,orientation);

    }

    private Tondeuse eastGoDroite(Tondeuse tondeuse) {
        Position  position = tondeuse.getPosition();
        Tondeuse.Orientation orientation = tondeuse.getOrientation();
        int yInitale = position.getX();
        if (yInitale > Action.minimumY) {
            LOGGER.info("Action autorisée");
            try {
                position.setX(yInitale - 1);
                orientation = Tondeuse.Orientation.S;
            } catch (OutOfBoundsException e) {
                LOGGER.error("Action non autorisé");
            }
        }return new Tondeuse(position,orientation);
    }

    private Tondeuse westGoDroite(Tondeuse tondeuse) {
        Position  position = tondeuse.getPosition();
        Tondeuse.Orientation orientation = tondeuse.getOrientation();
        int yInitale = position.getY();
        if (yInitale < coinSuperieur.getMaxY()) {
            LOGGER.info("Action autorisée");
            try {
                position.setX(yInitale + 1);
                orientation = Tondeuse.Orientation.N;
            } catch (OutOfBoundsException e) {
                LOGGER.error("Action non autorisé");
            }
        }return new Tondeuse(position,orientation);
    }

    private Tondeuse northGoDroite(Tondeuse tondeuse) {
        Position  position = tondeuse.getPosition();
        Tondeuse.Orientation orientation = tondeuse.getOrientation();
        int xInitale = position.getX();
        if (xInitale < coinSuperieur.getMaxX()) {
            LOGGER.info("Action autorisée");
            try {
                position.setX(xInitale + 1);
                orientation = Tondeuse.Orientation.E;
            } catch (OutOfBoundsException e) {
                LOGGER.error("Action non autorisé");
            }
        }return new Tondeuse(position,orientation);
    }
*/
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
        GoDroite goDroite =(GoDroite) obj;
        if(goDroite.coinSuperieur != ((GoDroite) obj).coinSuperieur){
            return false;
        }
        return true;

    }

}
