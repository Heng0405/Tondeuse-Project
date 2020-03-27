package actions;

import exceptions.OutOfBoundsException;
import models.Action;
import models.Position;
import models.TailleDePelouse;
import models.Tondeuse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoGauche extends Action {

    static final Logger LOGGER = LoggerFactory.getLogger(GoAvance.class);

    TailleDePelouse coinSuperieur;

    public GoGauche(final TailleDePelouse coinSuperieur) {
        super(coinSuperieur);
    }

    @Override
    public Tondeuse execute(Tondeuse tondeuse) throws OutOfBoundsException {
        LOGGER.info("Tondeuse prêt à tourner à gauche!");
        LOGGER.info("Position initiale : (" + tondeuse.getPosition().getX() + "," + tondeuse.getPosition().getY() + ")");
        LOGGER.info("Orientation initiale {}", tondeuse.getOrientation());

        switch (tondeuse.getOrientation()) {
            case N:
                northGoGauche(tondeuse);
                break;
            case W:
                westGoGauche(tondeuse);
                break;
            case E:
                eastGoGauche(tondeuse);
                break;
            case S:
                southGoGauche(tondeuse);
                break;
            default:
                throw new OutOfBoundsException("Out of boundry");
        }

        LOGGER.info("Action terminée!");
        LOGGER.info("Position finale ({} {})", tondeuse.getPosition().getX(), tondeuse.getPosition().getY());
        LOGGER.info("Orientation finale {}", tondeuse.getOrientation());


        return tondeuse;
    }

    private Tondeuse southGoGauche(Tondeuse tondeuse) {
        Position position = tondeuse.getPosition();
        Tondeuse.Orientation orientation = tondeuse.getOrientation();
        int xInitale = position.getX();
        if (xInitale < coinSuperieur.getMaxX()) {
            LOGGER.info("Action autorisé");
            try {
                position.setX(xInitale + 1);
                orientation = Tondeuse.Orientation.E;
            } catch (OutOfBoundsException e) {
                LOGGER.error("Action non autorisé");
            }
        }
        return new Tondeuse(position, orientation);

    }

    private Tondeuse eastGoGauche(Tondeuse tondeuse) {
        Position position = tondeuse.getPosition();
        Tondeuse.Orientation orientation = tondeuse.getOrientation();
        int yInitale = position.getX();
        if (yInitale < coinSuperieur.getMaxY()) {
            LOGGER.info("Action autorisée");
            try {
                position.setX(yInitale + 1);
                orientation = Tondeuse.Orientation.N;
            } catch (OutOfBoundsException e) {
                LOGGER.error("Action non autorisé");
            }
        }
        return new Tondeuse(position, orientation);
    }

    private Tondeuse westGoGauche(Tondeuse tondeuse) {
        Position position = tondeuse.getPosition();
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
        }
        return new Tondeuse(position, orientation);
    }

    private Tondeuse northGoGauche(Tondeuse tondeuse) {
        Position position = tondeuse.getPosition();
        Tondeuse.Orientation orientation = tondeuse.getOrientation();
        int xInitale = position.getX();
        if (xInitale > Action.minimumX) {
            LOGGER.info("Action autorisé");
            try {
                position.setX(xInitale - 1);
                orientation = Tondeuse.Orientation.W;
            } catch (OutOfBoundsException e) {
                LOGGER.error("Action non autorisé");
            }
        }
        return new Tondeuse(position, orientation);
    }

}
