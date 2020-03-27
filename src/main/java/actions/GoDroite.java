package actions;

import exceptions.OutOfBoundsException;
import models.Action;
import models.Position;
import models.TailleDePelouse;
import models.Tondeuse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoDroite extends Action {
    static final Logger LOGGER = LoggerFactory.getLogger(GoAvance.class);
    TailleDePelouse coinSuperieur;
    public GoDroite(final TailleDePelouse coinSuperieur){
        super(coinSuperieur);
    }


    public Tondeuse execute(Tondeuse tondeuse) {
        LOGGER.info("Tondeuse prêt à tourner à droite!");
        LOGGER.info("Position initiale : (" + tondeuse.getPosition().getX() + "," + tondeuse.getPosition().getY() + ")");
        LOGGER.info("Orientation initiale {}", tondeuse.getOrientation());

        switch (tondeuse.getOrientation()) {
            case N:
                northGoDroite(tondeuse);
                break;
            case W:
                westGoDroite(tondeuse);
                break;
            case E:
                eastGoDroite(tondeuse);
                break;
            case S:
                southGoDroite(tondeuse);
                break;
        }

        LOGGER.info("Action terminée!");
        LOGGER.info("Position finale ({} {})", tondeuse.getPosition().getX(), tondeuse.getPosition().getY());
        LOGGER.info("Orientation finale {}", tondeuse.getOrientation());
        return tondeuse;
    }

    private Tondeuse southGoDroite(Tondeuse tondeuse) {
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


}
